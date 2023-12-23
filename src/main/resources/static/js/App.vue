<script setup>
import {ref} from 'vue'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Main from './components/Main.vue'
import Login from "./components/Login.vue";

let auth = ref(false)

console.log("start")
</script>

<template>
  <Header/>
  <p>{{ auth }}</p>
  <Main v-if="auth"/>
  <Login @responses="(msg) => auth = msg" v-else-if="!auth"/>

  <Footer/>
</template>

