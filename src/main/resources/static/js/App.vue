<script setup>
import {ref} from 'vue'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Main from './components/Main.vue'
import Login from "./components/Login.vue"
import Auth from './components/Auth.vue'

let auth = ref(false)

console.log("start")
</script>

<template>
  <Header v-if="auth"/>
  <!-- <p>{{ auth }}</p> -->
  <Main v-if="auth"/>
  <Auth @responses="(msg) => auth = msg" v-else-if="!auth"/>

  <Footer v-if="auth"/>
</template>

