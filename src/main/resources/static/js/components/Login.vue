<script setup>
import {ref} from "vue";

const emit = defineEmits(['responses'])

let auth = ref(false)
function submit(){
    auth.value = !auth.value
    emit('responses', auth.value)
}

</script>

<template>
    <div class="main-container">
        <div class="container">
            <input type="text" class="name" placeholder="Введите имя">
            <input type="text" class="password" placeholder="Введите пароль">
            <button @click="submit" class="btn-auth">Авторизоваться</button>
        </div>
    </div>
</template>

<style scoped>
  .btn-auth{
      background: red;
  }
</style>
