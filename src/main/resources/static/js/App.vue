<template>
  <Header v-if="is"/>

  <router-view></router-view>

  <!-- <Footer v-if="is"/> -->
  
</template>

<script setup>
import { computed, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import Header from 'components/Header.vue'
import Footer from 'components/Footer.vue'

const router = useRouter()
const store = useStore()

const is = computed(() => store.state.isAuth)

router.beforeEach(async (to, from) => {
  store.commit('setAuthValue', localStorage.getItem('isLoggin'))
  
  // Если не авторизован пользователь, то открыт доступ к /login и /registration
  if (!is.value && to.name !== 'Login' && to.name !== 'Registration') {
    return { name: 'Login' }
  }
  //Если пользователь авторизован, то доступ к /login и /registration закрыт
  if(is.value && (to.name === 'Registration' || to.name === 'Login')){
    return { from }
  }

})

</script>

