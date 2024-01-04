<template>
  <Header v-if="isAuth"/>
  <!-- <p>{{ auth }}</p> -->
  <Main v-if="isAuth"/>
  <Auth @auth="(status) => isAuth = status" v-else-if="!isAuth"/>

  <Footer v-if="isAuth"/>
</template>

<script setup>
import {ref} from 'vue'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Main from './components/Main.vue'
import Auth from './components/Auth.vue'

let isAuth = ref(false)

console.log("start")

</script>

