<template>
    <Login @logToReg="(getFlag) => flag = getFlag" v-if="!flag"/>
    <Registration @regToLog="(getFlag) => flag = getFlag" v-else-if="flag"/>
</template>

<script setup>
import {ref} from "vue";
import Login from "./Login.vue"
import Registration from "./Registration.vue";


let flag = ref(false)

</script>

<style scoped>
    
</style>