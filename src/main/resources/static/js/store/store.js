import { createStore } from 'vuex'

export default createStore({
    state: {
        isAuth: false,
        canvas: {
            radiusInPixel: 25,
            centerX: 151.5,
            centerY: 151.5,
            widthAxisY: 3,
            heightAxisX: 3
        },
    },
    mutations: {
        setAuthValue (state, value) {
            state.isAuth = value
        }
    }
})