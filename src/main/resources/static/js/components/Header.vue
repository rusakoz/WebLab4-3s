<template>
    <div class="header">
        <div class="container">
            <div class="nav">
                <p class="info">Группа: P3223 Вариант: ...</p>
                <router-link to="/" class="logo">Кожемякин Руслан Алексеевич</router-link>
                <a @click="useLogout(store, router)" href="#" class="home" id="home-a">
                    <img src="/images/home.svg" alt="#" height="16" width="16"/>
                </a>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useLogout } from 'use/router/logout'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const router = useRouter()
const store = useStore()

</script>

<style scoped>

</style>
