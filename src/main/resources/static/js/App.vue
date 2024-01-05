<template>
  <Header @auth="(status) => flag = status" v-if="isLoggin"/>
  <!-- <p>{{ auth }}</p> -->
  <Main v-if="isLoggin"/>
  <Auth @auth="(status) => flag = status" v-else-if="!isLoggin"/>

  <Footer v-if="isLoggin"/>
</template>

<script setup>
import { ref } from 'vue'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Main from './components/Main.vue'
import Auth from './components/Auth.vue'
import { useIsLogged } from 'use/isLogged'

const isLoggin = ref(false)
const flag = ref(false)

useIsLogged(isLoggin, 'isLoggin', flag)

console.log("start")

</script>

