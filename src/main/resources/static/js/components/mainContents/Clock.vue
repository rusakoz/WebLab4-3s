<template>
    <button @click="router.push('/graph')" class="main-btn" id="main-btn" type="button">
        <span class="link-text">Fffffff</span>
        <span id="clock">{{ clock }}</span>
    </button>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const clock = ref()

onBeforeMount(function (){
    setInterval(()=>{clock.value = new Date().toLocaleDateString() + " " + new Date().toLocaleTimeString()}, 1000)
})

</script>

<style scoped>

</style>
