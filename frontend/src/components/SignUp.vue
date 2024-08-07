<template>
  <div class="mx-auto mt-5" style="width: 50%;">
  <div class="g-3 px-auto needs-validation" novalidate>
    
    <div>
      <label for="validationCustom01" class="form-label">User name</label>
      <input type="text" 
            class="form-control" 
            id="userName"  
            v-model="user.userName" >
      <div v-if="validateObjs.userNameError" class="error">
        이름을 입력하세요.
      </div>
    </div>

    <label for="userId" class="form-label">ID</label>
    <div class="input-group">
      <input type="text" 
            class="form-control" 
            id="userId"
            v-model="user.userId" >
      <button class="btn btn-secondary" type="button" id="button-addon2" @click="isDupId">
        중복확인
      </button>
    </div>
    <div v-if="validateObjs.userIdError" class="error"> {{ validateObjs.userIdError }} </div>
    <div v-if="!validateObjs.userIdIsDup" class="success"> 사용 가능합니다. </div>
    
    <div>
      <label for="password" class="form-label">Password</label>
      <div class="input-group has-validation">
        <input type="password" 
              class="form-control" 
              id="password" 
              maxlength="15"
              v-on:keyup="pwCheck"
              v-model="user.password" >
      </div>
      <div v-if="validateObjs.userPwError" class="error"> {{ validateObjs.userPwError }} </div>
    </div>
    
    <div>
      <label for="passwordCheck" class="form-label">Password check</label>
      <div class="input-group has-validation">
        <input type="password" 
              class="form-control" 
              id="passwordCheck"
              maxlength="15"
              v-model="user.passwordCheck"
              v-on:keyup="pwCheck" >
      </div>
      <div v-if="validateObjs.userPwCheckError" class="error"> {{ validateObjs.userPwCheckError }} </div>
    </div>  

    <div class="col-12 mt-5">
      <button class="btn btn-primary float-end" type="button" @click="submit">
        Submit form
      </button>
    </div>

  </div>
</div>
</template>

<script setup>
  import { reactive } from 'vue';
  import { $axios } from '@/api/index';
  import { useRouter } from 'vue-router';

  const api = $axios();
  const router = useRouter();

  let dupOkUserId = '';

  const user = reactive({
    userName : null,
    userId : null,
    password : null,
    passwordCheck : null
  });

  const validateObjs = reactive({
    userNameError : null,
    userIdError : null,
    userPwError : null,
    userPwCheckError : null,
    userIdIsDup: true
  });

  const isDupId = () => {
    api
      .post('/users/check-id', {
        userId : user.userId
      })
      .then(() => {
        validateObjs.userIdError = null;
        document.getElementById("userId").classList.remove("is-invalid");
        document.getElementById("userId").classList.add("is-valid");
        validateObjs.userIdIsDup = false;
        dupOkUserId = user.userId;

      })
      .catch((error) => {
        validateObjs.userIdError = error.response.data.split(":")[1];
        validateObjs.userIdIsDup = true;
        dupOkUserId = null;
        document.getElementById("userId").classList.remove("is-valid");
        document.getElementById("userId").classList.add("is-invalid");       
      })
      .finally(() => {

      })
  }

  const pwCheck = () => {
    if(user.password != user.passwordCheck){
      validateObjs.userPwCheckError = "동일한 PW를 입력하세요.";
      validateObjs.userPwError = "동일한 PW를 입력하세요.";
    } else {
      validateObjs.userPwCheckError = null;
      validateObjs.userPwError = null;
    }
  }

  const isValid = () => {
    if(!user.userName.trim()){
      validateObjs.userNameError = "User name을 입력하세요.";
      return false;
    }

    if(!user.userId){
      validateObjs.userIdError = "User Id를 입력하세요.";
      return false;
    } else {
      if(dupOkUserId != user.userId){
        validateObjs.userIdError = "User Id 중복확인을 해주세요.";
        validateObjs.userIdIsDup = true;
        document.getElementById("userId").classList.remove("is-valid");
        document.getElementById("userId").classList.add("is-invalid");        
        return false;
      }
    }

    if(user.password != user.passwordCheck){
      validateObjs.userPwCheckError = "PW 확인을 해주세요.";
      validateObjs.userPwError = "PW 확인을 해주세요.";
      return false;
    }

    return true;
  }

  const submit = () => {
    if(!isValid()) return;
    if(!confirm("가입하시겠습니까?")) return;

    api.
      post("/users/sign-up", user)
      .then(() => {
        window.alert("가입이 완료됐습니다!\n로그인을 해주세요!");
        router.push("/login");
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        
      })

  }

  


</script>

<style scoped>
  .error{
    color: rgb(198, 60, 60);
    font-size: 14px;
  }
  .success{
    color: #198754;
    font-size: 14px;
  }
</style>