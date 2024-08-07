import { defineStore } from "pinia";
import {jwtDecode} from 'jwt-decode';

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
            localStorage.removeItem("userName");
        },
        checkTokenExpiry(){
            if(!this.token) return false;

            try {
                const decodedToken = jwtDecode(this.token);
                const currentTime = Date.now() / 1000;
    
                if(decodedToken.exp < currentTime){
                    this.clearUser();
                    return false;
                }
                return true;
            } catch (error) {
                console.log("checkTokenExpiry(): ", error);
                return false;
            } 
        },
    },
    getters: {
        isAuthenticated: (state) => !!state.token
    }
})