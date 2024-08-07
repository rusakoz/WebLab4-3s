<template>
  <div class="auth-main-container">
      <div class="auth-container">
          <div class="auth-wrapper-container">
              <div class="login-container">
                <small v-if="regErr" class="errors">{{ regErrText }}</small>
                    <div class="form-control" :class="{invalid: !form.name.valid && form.name.touched}">
                      <label>Имя</label>
                      <input class="name" placeholder="Введите имя" v-model="form.name.value" @blur="form.name.blur">
                      <small v-if="form.name.errors.required && form.name.touched" class="errors">Введите имя</small>
                    </div>
                    <div class="form-control" :class="{invalid: !form.password.valid && form.password.touched}">
                      <label>Пароль</label>
                      <input class="password" type="password" id="firstPassword" placeholder="Введите пароль" v-model="form.password.value" @blur="form.password.blur">
                      <small v-if="form.password.errors.required && form.password.touched" class="errors">Введите пароль</small>
                      <small v-else-if="form.password.errors.minLength && form.password.touched" class="errors">
                        Символов введено {{ form.password.value.length }} из {{ minPassLength }}.
                      </small>
                    </div>
                    <div class="form-control" :class="{invalid: !form.equalPass && form.passwordTwo.touched}">
                      <label>Повторите пароль</label>
                      <input class="password" type="password" id="secondPassword" placeholder="Повторите пароль" v-model="form.passwordTwo.value" @blur="form.passwordTwo.blur">
                      <small v-if="form.passwordTwo.errors.required && form.passwordTwo.touched" class="errors">Введите пароль</small>
                      <small v-else-if="form.passwordTwo.errors.minLength && form.passwordTwo.touched" class="errors">
                        Символов введено {{ form.passwordTwo.value.length }} из {{ minPassLength }}.
                      </small>
                      <small v-else-if="!form.passwordTwo.errors.minLength && !form.equalPass.valid && form.passwordTwo.touched" class="errors">
                        Пароли не совпадают.
                      </small>
                    </div>
                    <button @click="submit" class="btn-auth" :disabled="!form.valid">Регистрация</button>
                    <h6 class="text-reg">Зарегистрированы?
                      <router-link to="/login" id="to-log" class="link-reg">Войти</router-link>
                    </h6>
              </div>
          </div>
      </div>
  </div>
</template>

<script setup>
import { ref } from "vue"
import { useFormReg } from "use/validForm/formReg"
import { useFetchPost } from "use/requests/fetchPost"
import { useRouter } from 'vue-router'

const router = useRouter()

const minPassLength = 6

const required = val => !!val
const minLength = num => val => val.length >= num

const form = useFormReg({
  name: {
    value: '',
    validators: {required}
  },
  password: {
    value: '',
    validators: {required, minLength: minLength(minPassLength)}
  },
  passwordTwo: {
    value: '',
    validators: {required, minLength: minLength(minPassLength)}
  }
})

const regErrText = ref('')
const regErr = ref(false)

async function submit(){

  const res = await useFetchPost("/user/reg", {name: form.name.value, 
                                                  password: form.password.value
                                                }
  ).catch(()=>{
      regErrText.value = "Ошибка регистрации"
      regErr.value = true
    })

    const resJson = res.json()

    if(res.status === 200 || res.status === 201){
      let errorText
      await resJson.then((res)=>{errorText = res.error})

      if(typeof errorText !== 'undefined'){
        regErrText.value = errorText
        regErr.value = true
      }else{
        router.push('/login')
      }
    }else{
      regErrText.value = "Ошибка регистрации"
      regErr.value = true
    }
}

</script>

<style scoped>

</style>