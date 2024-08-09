<template>
  <div class="container">
    <h2 class="me-auto my-4 fw-bolder">
        To-Do Create
    </h2>

    <h4>Subject</h4>
    <input type="text" 
          class="form-control" 
          v-model="todo.subject" 
          @input="validateSubject"
          :class="{'is-invalid' : subjectError}" />

    <div v-if="subjectError" class="error">{{ subjectError }}</div>

    <h4 class="mt-5">Body</h4>
    <textarea class="form-control" style="height: 300px;" v-model="todo.todoBody"></textarea>
    
    <div class="mt-5 mb-2 d-flex py-2">
      <h4 class="me-2" style="vertical-align: middle;">File</h4> 
      <button type="button" class="btn btn-primary" @click="addFileInput">
        <i class="bi bi-plus-square"></i>
      </button>
    </div>
    <div class="input-group mt-2"
          v-for="(file, index) in todo.files" :key="index">
      <input type="file" class="form-control"
              @change="handleFile($event, index)"
              multiple  />
       <button type="button" class="btn btn-outline-danger" @click="removeFileInput(index)" >
        <i class="bi bi-trash-fill"></i>
      </button>       
    </div>
  
    
    <div class="mt-4 d-flex btn-box">
      <button type="button" class="btn btn-secondary" @click="goBack">Cancel</button>
      <button type="button" class="btn btn-primary" @click="createTodo">Create</button>
    </div>
  </div>  
</template>

<script setup>
import { router } from '@/routers';
import { reactive, ref, computed, onMounted } from 'vue';
import { $axios } from '@/api/index';
import { useUserStore } from '@/store/userStore';

const api = $axios();
const userStore = useUserStore();

const todo = reactive({
  subject: null,
  todoBody: null,
  files: []
});

const subjectError = ref(null);
const token = userStore.token || '';

const addFileInput = () => {
      todo.files.push(null) // Add a null value to represent a new file input
}

const removeFileInput = (index) => {
  todo.files.splice(index, 1) // Remove the file input at the specified index
}

const handleFile = (event, index) => {
      todo.files[index] = event.target.files[0] // Update the file at the specified index
}

const isFormValid = computed(() => {
      return !subjectError.value && todo.subject.trim() !== '';
});

const validateSubject = () => {
  if(!todo.subject || !todo.subject.trim()){
    subjectError.value = '제목을 입력해주세요.';
  } else {
    subjectError.value = null;
  }
}

const isValid = () => {
  validateSubject();
  
  if(!isFormValid.value) {
    window.alert(subjectError.value);
    return false;
  }
  return true;
}


const createTodo = async () => {
  
  if(!isValid()) return;
  if(!window.confirm("저장하시겠습니까?")) return;

  const formData = new FormData();

  formData.append('subject', todo.subject);
  formData.append('body', todo.todoBody);
  todo.files.forEach((file, index) => {
    if (file) {
      formData.append(`files[${index}]`, file);
    }
  });

  try {
    const response = await api.post('/todos/todo', formData, {
      headers: {
        'Content-Type' : 'multipart/form-data',
        'Authorization' : `Bearer ${token}`
      }
    });

    if (response.data) goBack();
  } catch (error) {
    console.log(error);
  }
}

const goBack = () =>{router.push("/todos");}

//초기 파일 태그 1개 추가
onMounted(() => { todo.files.push(null) })
</script>

<style scoped>
  .container{
    width: 70%;
    margin: 0 auto;
  }

  .btn-box{
    justify-content: space-between;
  }

  .error{
    color: rgb(198, 60, 60);
    font-size: 14px;
  }
</style>