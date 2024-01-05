<template>
    <div class="header">
        <div class="container">
            <div class="nav">
                <p class="info">Группа: P3223 Вариант: ...</p>
                <a href="/" class="logo" id="logo-a">Кожемякин Руслан Алексеевич</a>
                <a @click="logout" href="#" class="home" id="home-a">
                    <img src="/images/home.svg" alt="#" height="16" width="16"/>
                </a>
            </div>
        </div>
    </div>
</template>

<script setup>

const emit = defineEmits(['auth'])

function logout(){
    localStorage.removeItem('userAccessToken')
    localStorage.removeItem('userRefreshToken')
    localStorage.removeItem('isLoggin')
    emit('auth', false)
}

</script>

<style scoped>

</style>
