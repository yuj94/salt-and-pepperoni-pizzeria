<template>
  <div class="bannerDiv">
    <h1 id="heading">Salt & Pepperoni Pizzeria</h1>
    <nav id="navigation">
      <div>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ path: '/pizzas' }">Menu</router-link>
      </div>
      <div>
        <router-link
          v-bind:to="{ name: 'login' }"
          v-if="this.$store.state.token == ''"
          >Employee Login</router-link
        > <router-link v-bind:to="{ name: 'logout'}" v-else>Logout</router-link>
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
  border: solid 1px black;
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: "heading heading navigation";
  
}

.bannerDiv > #heading {
  color: #d20201;
  grid-area: heading;
  display: flex;
  align-items: center;
}

#navigation {
  text-align: center;
  justify-content: center;
  grid-area: navigation;
  display: flex;
  flex-direction: column;
}
</style>