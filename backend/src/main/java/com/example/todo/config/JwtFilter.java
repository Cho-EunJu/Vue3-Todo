package com.example.todo.config;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todo.exception.CustomException;
import com.example.todo.exception.ErrorCode;
import com.example.todo.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter{
	
    private static final Set<String> EXCLUDED_PATHS = Set.of(
            "/api/v1/users/sign-up", 
            "/api/v1/users/login", 
            "/api/v1/users/check-id"
    );	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//0) Excluded path 확인
        String requestPath = request.getRequestURI();

        if (EXCLUDED_PATHS.contains(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }		

		//1) header 에서 token 꺼내기
		final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		//2) header에 token 없을 경우 Block --> Exception 발생 안함
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			log.error("authorizationHeader 잘못보냈습니다.");
			setErrorResponse(ErrorCode.JWT_EXCEPTION.getHttpStatus(), response, "형식이 유효하지 않습니다. 재로그인 해주세요.");
			return;
			//throw new CustomException(ErrorCode.JWT_EXCEPTION, "형식이 유효하지 않습니다. 재로그인 해주세요.");
		}
		
		//3) token 꺼내기
		String token = authorizationHeader.split(" ")[1];
		
		//4) token 만료 확인 --> io.jsonwebtoken.ExpiredJwtException 발생
		try {
			if(JwtUtil.isExpired(token)) {
				log.error("토큰 만료");
				setErrorResponse(ErrorCode.JWT_EXCEPTION.getHttpStatus(), response, "세션이 만료되었습니다. 재로그인 해주세요.");
				return;
				//throw new CustomException(ErrorCode.JWT_EXCEPTION, "세션이 만료되었습니다. 재로그인 해주세요.");		
			}
		} catch (ExpiredJwtException e) {
			setErrorResponse(ErrorCode.JWT_EXCEPTION.getHttpStatus(), response, "세션이 만료되었습니다. 재로그인 해주세요.");
			return;
			//throw new CustomException(ErrorCode.JWT_EXCEPTION, "세션이 만료되었습니다. 재로그인 해주세요.");
		} 
		
		//5) userId 꺼내기
		String userId = JwtUtil.getUserId(token);
		
		//6) userId 셋팅
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("USER")));
		
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);		
	}
	

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
