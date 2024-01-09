<template>
   <div class="main">
        <div class="alert">
            <h3>Время авторизации вышло, повторите авторизацию</h3>
            <button @click="redirectToAuth" class="button">Окей</button>
        </div>
   </div> 
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function redirectToAuth(){
    router.replace('/login')
}

</script>

<style scoped>
    .main {
        display: flex;
        min-height: 100vh;
        background-color: bisque;
        justify-content: center;
        align-content: center;
    }

    .alert {
        display: flex;
        justify-content: center;
        align-content: center;
        flex-direction: column;
        background-color: aliceblue;
        height: 300px;
        width: 600px;
        box-shadow: 0px 0px 5px 5px rgba(0, 0, 0, 0.5)
    }

    .button {
        background-color: rgb(139, 135, 135);
        color: white;
        min-width: 25px;
        min-height: 15px;
    }

    .button:hover {
        background-color: white;
        color: rgb(139, 135, 135);
        transition: all 0.2s;
        cursor: pointer;
    }

</style>