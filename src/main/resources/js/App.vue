<template>
  <Header v-if="isAuth"/>

  <router-view></router-view>

  <Footer v-if="isAuth"/>
  
</template>

<script setup>
import { computed } from 'vue'
import { useStore } from 'vuex'
import Header from 'components/Header.vue'
import Footer from 'components/Footer.vue'
import { useGuardRouter } from 'router/guardRouter'

const store = useStore()
const isAuth = computed(()=>store.state.isAuth)

useGuardRouter()

</script>