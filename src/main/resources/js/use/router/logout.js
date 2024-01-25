export function useLogout(store, router){ 
    localStorage.clear()
    store.commit('setAuthValue', false)
    router.replace('/login')
}