import { createApp } from 'vue'
import '@babel/polyfill'
import App from './App.vue'
import router from 'router/router'
import store from 'store/store'
import './assets/style.css'

const app = createApp(App)

app.use(store)
app.use(router)

app.mount('#app')

