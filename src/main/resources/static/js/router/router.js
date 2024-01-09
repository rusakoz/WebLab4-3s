import { createRouter, createWebHistory } from 'vue-router'
import App from 'App.vue'
import Login from 'components/Login.vue'
import Registration from 'components/Registration.vue'
import Graph from 'components/mainContents/Graph.vue'
import Hello from 'components/mainContents/Hello.vue'
import AuthAlert from 'components/alerts/AuthAlert.vue'

const routes = [
    { path: '/', component: Hello },
    { path: '/login', name: 'Login', component: Login },
    { path: '/registration', name: 'Registration', component: Registration },
    { path: '/graph', component: Graph },
    { path: '/hello', component: Hello },
    { path: '/authAlert', component: AuthAlert },
]

export default createRouter({
    history: createWebHistory(),
    routes
})