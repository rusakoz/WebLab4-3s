<template>
    <button @click="toGraph" class="main-btn" id="main-btn" type="button">
        <span class="link-text">Fffffff</span>
        <span id="clock">{{ message }}</span>
    </button>
</template>

<script setup>
import {onBeforeMount, ref} from 'vue'
let message = ref()

onBeforeMount(function (){
    setInterval(()=>{message.value = new Date().toLocaleDateString() + " " + new Date().toLocaleTimeString()}, 1000)
})

const emit = defineEmits(['toGraph'])

function toGraph(){
    console.log('emit')
    emit('toGraph')
}

</script>

<style scoped>

</style>
