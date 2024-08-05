<template>
    <main class="form-signin w-50 mt-5 mx-auto">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" placeholder="ID" v-model="userId">
            <label for="floatingInput">ID</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <!-- <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
                Remember me
            </label> -->
        </div>
        <button class="btn btn-primary w-100 py-2" type="button" @click="login">Sign in</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </main>
</template>

<script setup>
    import { ref } from 'vue';
    import { useUserStore } from '@/store/userStore';
    import { $axios } from '@/api/index';
    import { router } from '@/routers/index';

    const userId = ref('');
    const password = ref('');
    const userStore = useUserStore();
    const api = $axios();

    const login = async () => {
        try {
            const response = await api.post('/api/v1/users/login', {
                userId : userId.value,
                password: password.value
            });

            const {token, userName} = response.data;
            userStore.setUser({token, userName});
            
            window.alert(`환영합니다! ${userName} 님!`);
            router.push("/");

        } catch (error) {
            console.log("LOGIN ERROR!");
            console.log(error);
        }
    }
</script>

<style></style>