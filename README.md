# vue3-todo
- Vue.js 3 + SpringBoot 3 연동
- 기본적인 To-do List CRUD 와 JWT 를 이용한 인증 로직을 다뤄봅니다.


## Project start
```
npm run serve
```

### Skill & library
- SpringBoot 3
- Vue.js 3
- MariaDB 10
<br>

- JPA
- RestAPI
- JWT
- Spring Security
<br>

- Vue Router
- Axios
- Pinia
<br>
  
- Bootstrap 5


  추후 파일 등록을 위한 추가 있을 수 있음.

### 구현 완료
- 회원 가입을 할 수 있다. (이름, ID, Password)
  - ID의 경우 중복확인을 한다.
- 로그인을 할 수 있다. (Spring Security + JWT 발급)
- 사전에 정의해둔 url 을 제외하고는 JWT Filter 에서 권한 확인 한다.
- 현재 로그인 한 사용자의 to-do list를 조회한다.
- 체크박스 클릭시 토글 방식으로 완료 여부를 변경할 수 있다.


### 구현 중
- 검색어를 이용해 특정 to-do를 검색할 수 있다. 
- 페이징 처리
- to-do 등록 (file도 등록)
- to-do 수정
- to-do 삭제
- Mypage 에서 이름 / password 수정



