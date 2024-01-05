<template>
    <div class="main-container">
        <div class="container">
            <h1 class="title">Hello my dear friend</h1>
            <p class="subtitle">This is demo site</p>
            <p class="top-text">Press F for change location</p>
            <Clock @toGraph="toGraph"/>
        </div>
    </div>
</template>

<script setup>
import Clock from 'components/Clock.vue'

const emit = defineEmits(['toGraph'])

function toGraph(){
    emit('toGraph')
}
</script>

<style scoped>

</style>