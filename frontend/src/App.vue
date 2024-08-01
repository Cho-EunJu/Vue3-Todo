<template>
  <div class="name">{{ name }}</div>
  <button class="btn btn-primary" v-on:click="updateName">
    Click
  </button>
</template>

<script>
import { ref } from 'vue';

export default{
  setup(){
    //숫자 or 문자열 or boolean은 ref
    //객체는 reactive
    let name = ref("Ellen ");

    const updateName = () => {
      //ref로 선언한 reactive 변수의 값을 변경할 때는 .value 를 사용! 
      name.value = "ChoCho ";
      console.log(name);
    }

    return {name, updateName};
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.name{color: red;}
</style>
