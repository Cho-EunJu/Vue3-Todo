<template>
  <Header />
  <router-view class="px-5"></router-view>
</template>

<script setup>
// import { ref } from 'vue';
import Header from './layouts/Header.vue';

//숫자 or 문자열 or boolean은 ref
//객체는 reactive
  // let name = ref("Ellen ");

  // const updateName = () => {
  //   //ref로 선언한 reactive 변수의 값을 변경할 때는 .value 를 사용! 
  //   name.value = "ChoCho ";
  //   console.log(name);
  // }

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin: 0 auto;
  
}

.name{color: red;}
</style>
