import { createStore } from 'vuex'

export default createStore({
    state: {
        isAuth: false,
    },
    mutations: {
        setAuthValue (state, value) {
            state.isAuth = value
        }
    }
})