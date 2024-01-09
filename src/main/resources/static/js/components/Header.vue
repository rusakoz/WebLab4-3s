<template>
    <div class="header">
        <div class="container">
            <div class="nav">
                <p class="info">Группа: P3223 Вариант: ...</p>
                <router-link to="/" class="logo">Кожемякин Руслан Алексеевич</router-link>
                <!-- <a href="/" class="logo" id="logo-a">Кожемякин Руслан Алексеевич</a> -->
                <a @click="logout" href="#" class="home" id="home-a">
                    <img src="/images/home.svg" alt="#" height="16" width="16"/>
                </a>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

const router = useRouter()
const store = useStore()

function logout(){
    localStorage.clear()
    store.commit('setAuthValue', false)
    router.replace('/login')
}

</script>

<style scoped>

</style>
