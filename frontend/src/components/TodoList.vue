<template>
  <div class="container">
    <div class="clearfix d-flex mt-4">
      <h2 class="me-auto">
        To-Do List
      </h2>
      <RouterLink to="/todos/new" class="btn btn-primary float-end fw-bolder" style="height: 40px;">
        Create To-Do
      </RouterLink>
    </div>
    <!-- <hr> -->
    <div class="input-group my-5" style="width: 50%; margin: 0 auto;">
      <input type="text" class="form-control" placeholder="search.." aria-label="Recipient's username" aria-describedby="button-addon2">
      <button class="btn btn-outline-primary" type="button" id="button-addon2">
        <i class="bi bi-search"></i>
      </button>
    </div>
    <hr>

    <div class="item-container" v-if="!errorMsg.value">
      <div class="input-group mb-3" v-for="item in todos" :key="item.id">
        <div class="input-group-text">
          <input class="form-check-input mt-0" 
                  type="checkbox" aria-label="Checkbox for following text input" 
                  @click="checkTodo(item.id)"  :checked="item.completed === 'Y'">
        </div>
        <p class="form-control mb-0" :class="['form-control mb-0', item.completed === 'Y' ? 'todo-completed' : '']">{{ item.subject }}</p>
      </div>
    </div>
    <div class="item-container" v-else>
      {{ errorMsg.value }}
    </div>
    
  </div>
</template>

<script setup>
  import { $axios } from '@/api/index';
  import { ref, onMounted } from 'vue';
  import { useUserStore } from '@/store/userStore';

  const api = $axios();
  const todos = ref([]);

  const userStore = useUserStore();

  const token = userStore.token || '';
  const errorMsg = ref('');

  const fetchTodos = async () => {
    try {
      const response = await api.get('/todos/list', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      todos.value = response.data;

    } catch (error) {
      console.log(errMsg + "\n" + error);
      const errMsg =  error.response?.data?.message || 'Failed to fetch todos';
      errorMsg.value = errMsg;
    }
  }

  onMounted(fetchTodos);

</script>

<style scoped>
  .item-container{
    width: 80%;
    padding: 10px;
  }

  .todo-completed{
    text-decoration: line-through;
  }
</style>