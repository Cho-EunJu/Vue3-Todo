<template>
<header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <RouterLink to="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <i class="bi bi-house me-1"></i>
        </RouterLink>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li>
            <RouterLink to="/" :class="['nav-link px-2', route.path === '/' ? 'text-secondary' : 'text-white']">
              Home
            </RouterLink>
          </li>
          <li>
            <RouterLink to="/todos" :class="['nav-link px-2', route.path.startsWith('/todos') ? 'text-secondary' : 'text-white']">
              To-do
            </RouterLink>
          </li>
          <!-- <li><a href="#" class="nav-link px-2 text-white">Pricing</a></li>
          <li><a href="#" class="nav-link px-2 text-white">FAQs</a></li>
          <li><a href="#" class="nav-link px-2 text-white">About</a></li> -->
        </ul>

        <div class="text-end" v-if="isAuthenticated">
          <RouterLink to="/mypage" class="my-name">{{ userStore.userName }} 님</RouterLink>
          <button class="btn btn-secondary" type="button" @click="logout">Logout</button>
        </div>
        <div class="text-end" v-else>
          <RouterLink to="/login" class="btn btn-outline-light me-2">Login</RouterLink>
          <RouterLink to="/sign-up" class="btn btn-warning">Sign-up</RouterLink>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { useRouter, useRoute, RouterLink } from 'vue-router';
import { useUserStore } from '@/store/userStore';
import { computed } from 'vue';

const userStore = useUserStore();
const isAuthenticated = computed(() => userStore.isAuthenticated); // 상태에 접근 반응형
const router = useRouter();
const route = useRoute();


const logout = () => {
  userStore.clearUser();
  router.push("/");
}

</script>

<style scoped>
  .bi-house{
    font-size: 26px;
  }
  .bi{
    vertical-align: -.125em;
  }

  .my-name{
    padding-right: 10px;
    text-decoration: none;
    color: aliceblue;
  }
</style>