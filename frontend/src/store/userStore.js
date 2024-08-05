import { defineStore } from "pinia";
import jwt_decode from 'jwt-decode';

export const useUserStore = defineStore('user', {
    state: () => ({
        token: localStorage.getItem("token"),
        userName: localStorage.getItem("userName")
    }),
    actions: {
        setUser({token, userName}){
            this.token = token;
            this.userName = userName;
            localStorage.setItem("token", token);
            localStorage.setItem("userName", userName);
        },
        clearUser(){
            this.token = null;
            this.userName = null;
            localStorage.removeItem("token");
            localStorage.removeItem("token");
        },
        checkTokenExpiry(){
            if(!this.token) return false;

            try {
                const decodedToken = jwt_decode(this.token);
                const currentTime = Date.now() / 1000;
    
                if(decodedToken.exp < currentTime){
                    console.log("checkTokenExpiry()");   
                    this.clearUser();
                    return false;
                }
                return true;
            } catch (error) {
                console.log(error);
                return false;
            } 
        },
    },
    getters: {
        isAuthenticated: (state) => !!state.token
    }
})