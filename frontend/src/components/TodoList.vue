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
    
    
  </div>
</template>

<script setup>

</script>

<style>

</style>