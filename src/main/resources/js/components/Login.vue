<template>
  <div class="auth-main-container">
    <div class="auth-container">
      <div class="auth-wrapper-container">
        <div class="login-container">
          <small v-if="authErr" class="errors">{{ authErrText }}</small>
          <div class="form-control" :class="{ invalid: !form.name.valid && form.name.touched }">
            <label>Имя</label>
            <input class="name" placeholder="Введите имя" v-model="form.name.value" @blur="form.name.blur">
            <small v-if="form.name.errors.required && form.name.touched" class="errors">Введите имя</small>
          </div>
          <div class="form-control" :class="{ invalid: !form.password.valid && form.password.touched }">
            <label>Пароль</label>
            <input class="password" type="password" placeholder="Введите пароль" v-model="form.password.value"
              @blur="form.password.blur">
            <small v-if="form.password.errors.required && form.password.touched" class="errors">Введите пароль</small>
            <small v-else-if="form.password.errors.minLength && form.password.touched" class="errors">
              Символов введено {{ form.password.value.length }} из {{ minPassLength }}.
            </small>
          </div>
          <button @click="submit" class="btn-auth" :disabled="!form.valid">Войти</button>
          <h6 class="text-reg">Нет аккаунта?
            <router-link to="/registration" class="link-reg">Регистрация</router-link>
          </h6>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue"
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { useForm } from "use/validForm/form"
import { useFetchPost } from "use/requests/fetchPost"

const router = useRouter()
const store = useStore()

const required = val => !!val
const minLength = num => val => val.length >= num
const minPassLength = 6

const authErr = ref(false)
const authErrText = ref('')

const form = useForm({
  name: {
    value: '',
    validators: { required }
  },
  password: {
    value: '',
    validators: { required, minLength: minLength(minPassLength) }
  }
})

async function submit() {

  const response = await useFetchPost("/user/login",
                                                  {
                                                    userLogin: form.name.value,
                                                    userPassword: form.password.value
                                                  }
  ).catch((err) => {
    authErrText.value = "Ошибка авторизации(" + err + ")"
    authErr.value = true
  })

  if (response.status === 200) {
    const resJson = await response.json()

    const hasError = Boolean(resJson.error)

    if (hasError) {
      authErrText.value = resJson.error
      authErr.value = true
    } else {
      localStorage.setItem('userAccessToken', resJson.accessToken)
      localStorage.setItem('userRefreshToken', resJson.refreshToken)
      localStorage.setItem('isLoggin', true)
      store.commit('setAuthValue', true)

      router.replace('/hello')
    }
  } else {
    authErrText.value = "Ошибка авторизации(Статус: " + response.status + ")"
    authErr.value = true
  }
}

</script>
