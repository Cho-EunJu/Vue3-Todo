<template>
  <div class="container">
    <h2 class="me-auto my-4 fw-bolder">
        To-Do Create
    </h2>

    <h4>Subject</h4>
    <input type="text" 
          class="form-control" 
          v-model="subject" 
          @input="validateSubject"
          :class="{'is-invalid' : subjectError}" />

    <div v-if="subjectError" class="error">{{ subjectError }}</div>

    <h4 class="mt-5">Body</h4>
    <textarea class="form-control" style="height: 300px;" v-model="todoBody"></textarea> 
    
    <div class="mt-4 d-flex btn-box">
      <button type="button" class="btn btn-secondary" @click="goBack">Cancel</button>
      <button type="button" class="btn btn-primary" @click="createTodo">Create</button>
    </div>
  </div>  
</template>

<script setup>
import { router } from '@/routers';
import { ref, computed } from 'vue';
import { $axios } from '@/api/index';

const api = $axios();

const subject = ref('');
const subjectError = ref(null);
const todoBody = ref(null);

const validateSubject = () => {
  if(!subject.value.trim()){
    subjectError.value = '제목을 입력해주세요.';
  } else {
    subjectError.value = null;
  }
}

const isFormValid = computed(() => {
      return !subjectError.value && subject.value.trim() !== '';
});

const isValid = () => {
  validateSubject();
  
  if(!isFormValid.value) {
    window.alert(subjectError.value);
    return false;
  }
  return true;
}


const createTodo = () => {
  
  if(!isValid()) return;
  if(!window.confirm("저장하시겠습니까?")) return;

  api
    .post('/todos/todo', {subject: subject.value, body: todoBody.value, userId: 1})
    .then(() => {
      window.alert("저장 완료했습니다.");
      goBack();
    })
    .catch((error) => {
      console.log(error);
    })
    .finally(() => {
      
    })
  
}

const goBack = () =>{router.push("/todos");}
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