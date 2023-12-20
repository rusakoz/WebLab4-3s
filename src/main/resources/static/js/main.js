import { createApp } from 'vue'
import App from 'components/App.vue'

createApp(App).mount('#app')

console.log("dasdas")
/*//корневой компонент на самом деле ничем не отличается от любого другого компонента
const RootComponent = {
    data() {
        return {
            message: new Date(),
            message2: 23123
        }
    },
    beforeMount() {
        setInterval(()=>{this.message = new Date()}, 1000)
    },
    methods: {
        increment(){
            this.message2++
        }
    }
}
console.log("dsadas")
const app = Vue.createApp(RootComponent)
const vm = app.mount('#app')*/
