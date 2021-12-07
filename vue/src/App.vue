<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'menu' }">Menu</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
*,
*::before,
*::after {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
  background: #F6F2ED;
}

.loading {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>