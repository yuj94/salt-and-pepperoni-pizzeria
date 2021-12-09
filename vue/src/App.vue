<template>
  <div id="app">
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