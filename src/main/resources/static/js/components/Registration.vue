<script setup>
import {ref} from "vue";

const emit = defineEmits(['responses'])

let auth = ref(true)

function toLogin(){
    auth.value = !auth.value
    emit('resp', auth.value)
}

</script>

<template>
    <div class="main-container">
        <div class="container">
            <div class="wrapper-container">
                <div class="login-container">
                    <input type="text" class="name" placeholder="Введите имя">
                    <input type="text" class="password" placeholder="Введите пароль">
                    <input type="text" class="password" placeholder="Повторите пароль">
                    <button class="btn-auth">Регистрация</button>
                    <h6 class="text-reg">Зарегистрированы? <a @click="toLogin" class="link-reg" href="#">Войти</a></h6>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    .main-container{
        background: linear-gradient(180deg, darkmagenta 0%, rgba(80, 0, 250, 0.93) 100%);
        min-height: 100vh;
    }

    .container{
        min-height: 100vh;
    }
    .wrapper-container {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
  .login-container{
    display: flex;
    padding-left: 5px;
    padding-right: 5px;
    flex-direction: column;
    width: calc(100vw/100*15);
    height: calc(100vh/100*55);
    align-items: center;
    justify-content: center;
    background: linear-gradient(180deg, rgb(220, 224, 222) 0%, rgba(142, 145, 144, 0.93) 100%);
    margin-top: 25px;
    border-radius: 1rem;
    box-shadow: 0px 0px 5px 5px rgba(0, 0, 0, 0.5)
    
  }

  .name{
    height: 30px;
    width: 100%;
    border-radius: 0.5rem;
    border: 0;
  }

  .name:focus{
    color: #212529;
    background-color: #fff;
    border-color: #bdbdbd;
    outline: 0;
    box-shadow: 0 0 0 0.1rem rgba(71, 71, 71, 0.25);
  }

  .password{
    height: 30px;
    border-radius: 0.5rem;
    border: 0;
    margin-top: 7px;
    width: 100%;
  }

  .password:focus{
    color: #212529;
    background-color: #fff;
    border-color: #bdbdbd;
    outline: 0;
    box-shadow: 0 0 0 0.1rem rgba(71, 71, 71, 0.25);
  }

  .btn-auth{
    margin-top: 10px;
    max-width: 100%;
    border-radius: 1rem;
    border: 0;
    background-color: rgb(255, 255, 255);
    color: rgb(0, 0, 0);
  }

  .btn-auth:hover{
    background-color: rgb(30, 20, 98);
    color: rgb(255, 255, 255);
    transition: all 0.2s;
    cursor: pointer;
  }

  .text-reg{
    font-size: calc(100vh/60);
  }

  .link-reg{
    color: rgb(30, 20, 98);
  }
</style>