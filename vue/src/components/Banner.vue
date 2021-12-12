<template>
  <div class="bannerDiv">
    <h1 id="heading">Salt & Pepperoni Pizzeria</h1>
    <nav id="navigation">
      <div id="navigation-row">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        &nbsp;|&nbsp;
        <router-link v-bind:to="{ path: '/pizzas' }">Menu</router-link>
      </div>
      <div id="navigation-row">
        <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Employee Login</router-link>
        <router-link v-bind:to="{ name: 'logout'}" v-else>Logout</router-link>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "banner",
};
</script>

<style scoped>
.bannerDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: "heading heading navigation";
  height: 75px;
  border-bottom: solid 1px black;
}

.bannerDiv > #heading {
  grid-area: heading;
  display: flex;
  align-items: center;
  color: #d20201;
  padding-left: 40px;
}

#navigation {
  grid-area: navigation;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}

#navigation-row > a {
  color: black;
  text-decoration: none;
}

.router-link-exact-active {
  color: #d20201 !important;
  font-weight: bold;
}
</style>