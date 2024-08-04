import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export function useGuardRouter(){
    const router = useRouter()
    const store = useStore()

    const isAuth = computed(()=>store.state.isAuth)

    router.beforeEach(async (to, from) => {
      store.commit('setAuthValue', localStorage.getItem('isLoggin'))

      // Если не авторизован пользователь, то открыт доступ к /login и /registration и /authAlert(для принудительного разлогина)
      if (!isAuth.value && to.name !== 'Login' && to.name !== 'Registration' && to.name !== 'AuthAlert') {
        return { name: 'Login' }
      }
      //Если пользователь авторизован, то доступ к /login и /registration закрыт
      if(isAuth.value && (to.name === 'Registration' || to.name === 'Login')){
        return { from }
      }
    
    })
}