const Message = {
    data() {
        return {
            message: 0
        }
    },
    methods: {
        increment(){
            this.message++
        }
    }
}

Vue.createApp(Message).mount('#app')