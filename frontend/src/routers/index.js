import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/store/userStore";

import Main from "@/components/Main.vue";
import TodoList from "@/components/TodoList.vue";
import TodoNew from "@/components/TodoNew.vue";
import Login from "@/components/Login.vue";
import SignUp from "@/components/SignUp.vue";

const routes = [
    {path: '/', component: Main},
    {path: '/todos', component: TodoList},
    {path: '/todos/new', component: TodoNew},
    {path: '/login', component: Login},
    {path: '/sign-up', component: SignUp},
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    const userStore = useUserStore();
    
    if(to.path.startsWith('/todos')){
        if(!userStore.checkTokenExpiry()){
            window.alert("로그인 후 이용할 수 있는 메뉴입니다.");
            next("/login");
            return;
        } 
    }

    next();
})

export { router }