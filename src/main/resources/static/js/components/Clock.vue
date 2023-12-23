<script setup>
import {onBeforeMount, onMounted, ref} from 'vue'
let message = ref()

onBeforeMount(function (){
    setInterval(()=>{message.value = new Date().toLocaleDateString() + " " + new Date().toLocaleTimeString()}, 1000)
})
</script>

<template>
    <button class="main-btn" id="main-btn" type="button">
        <span class="link-text">Fffffff</span>
        <span id="clock">{{ message }}</span>
    </button>
</template>

<style scoped>

</style>
