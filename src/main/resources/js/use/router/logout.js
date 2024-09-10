import { useFetchPostJwt } from 'use/requests/fetchPostJwt'
export async function useLogout(store, router){
    await useFetchPostJwt('/logout', {name: 'ruslan432423'})
    localStorage.clear()
    store.commit('setAuthValue', false)
    router.replace('/login')
}