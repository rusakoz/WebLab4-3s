<script setup>
import {ref} from "vue";
import Login from "./Login.vue"
import Registration from "./Registration.vue";


let flag = ref(false)
// function submit(){

// }

</script>

<template>
    <Login @resp="(getFlag) => flag = getFlag" v-if="!flag"/>
    <Registration @resp="(getFlag) => flag = getFlag" v-else-if="flag"/>
</template>

<style scoped>
    
</style>