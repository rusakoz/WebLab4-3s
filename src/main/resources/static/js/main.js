//корневой компонент на самом деле ничем не отличается от любого другого компонента
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

const app = Vue.createApp(RootComponent)
const vm = app.mount('#app')