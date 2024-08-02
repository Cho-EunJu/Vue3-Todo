import { createRouter, createWebHistory } from "vue-router";

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

export {router}