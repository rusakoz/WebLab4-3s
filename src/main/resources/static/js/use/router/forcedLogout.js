export function useForcedLogout(store, router){ 
    localStorage.clear()
    store.commit('setAuthValue', false)
    router.replace('/authAlert')
}