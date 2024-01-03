<template>
    <div class="main-container">
        <div class="container">
            <div class="wrapper-container">
                <div class="login-container">
                  <!-- <pre>{{ form }}</pre> -->
                    <small v-if="authErr" class="errors">Неверный логин или пароль</small>
                    <div class="form-control" :class="{invalid: !form.name.valid && form.name.touched}">
                      <label>Имя</label>
                      <input class="name" placeholder="Введите имя" v-model="form.name.value" @blur="form.name.blur">
                      <small v-if="form.name.errors.required && form.name.touched" class="errors">Введите имя</small>
                    </div>
                    <div class="form-control" :class="{invalid: !form.password.valid && form.password.touched}">
                      <label>Пароль</label>
                      <input class="password" placeholder="Введите пароль" v-model="form.password.value" @blur="form.password.blur">
                      <small v-if="form.password.errors.required && form.password.touched" class="errors">Введите пароль</small>
                      <small v-else-if="form.password.errors.minLength && form.password.touched" class="errors">
                        Введено {{ form.password.value.length }} символов из 8.
                      </small>
                    </div>
                    <button @click="submit" class="btn-auth" :disabled="!form.valid">Войти</button>
                    <h6 class="text-reg">Нет аккаунта? <a @click="toReg" class="link-reg" href="#">Регистрация</a></h6>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref} from "vue";
import { useForm } from "use/form";

const emit = defineEmits(['responses'])

const required = val => !!val
const minLength = num => val => val.length >= num

let authErr = ref(false)

const form = useForm({
  name: {
    value: '',
    validators: {required}
  },
  password: {
    value: '',
    validators: {required, minLength: minLength(8)}
  }
})

console.log(form.password)

let auth = ref(false)
let reg = ref(false)

async function submit(){
    const formData = {
      userName: "string",
      userPassword: "string"
    }
    console.log(form.name.value)
    console.log(form.password.value)
    const res = await fetch("/user/login", {
      method: "POST",
      headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
      },
      body: JSON.stringify(
        {
        userLogin: form.name.value, 
        userPassword: form.password.value
        })
    })
    const resJson = res.json()
    if(res.status === 200){
      resJson.then((res)=>{console.log(res)})
      auth.value = !auth.value
      emit('responses', auth.value)
    }else{
      authErr.value = true
      resJson.then((res)=>{console.log(res.error)})
    }
}

function toReg(){
    reg.value = !reg.value
    emit('resp', reg.value)
}

</script>

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
  
  .form-control.invalid input{
    border: 2px solid;
    border-color: rgb(216, 97, 0);
  }

  .errors{
    color: rgb(28, 28, 28);
    font-size: 11px;
    margin-left: 3px;
  }

  .btn-auth:disabled{
    cursor: default;
    color: #212529;
    background-color: #848688;
  }

</style>
