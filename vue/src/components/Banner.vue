<template>
  <div class="bannerDiv">
    <div class="bannerGrid" v-if="isHomePage">
      <div id="login" class="homePageLogin">
        <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Employee Login</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-else>Logout</router-link>
      </div>
    </div>
    <div class="bannerGrid" v-else>
      <div id="logo">
        <router-link v-bind:to="{ name: 'home' }"><h1>Salt & Pepperoni Pizzeria</h1></router-link>
      </div>
      <div id="login" class="nonHomePageLogin">
        <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Employee Login</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-else>Logout</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "banner",
  computed: {
    isHomePage() {
      if (this.$route.path == "/") {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.bannerGrid {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: "logo logo login";
  height: 100px;
  padding: 0 24px;
}

#logo {
  grid-area: logo;
  display: flex;
  align-items: center;
}

#login {
  grid-area: login;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

#logo > a,
#login > a {
  text-decoration: none;
  font-size: 20px;
  font-weight: bold;
}

#logo > a {
  color: #d20201;
}

.homePageLogin > a {
  color: #fff;
}

.nonHomePageLogin > a {
  color: #000;
}

.homePageLogin > a:hover {
  color: rgba(255, 255, 255, 0.5);
  transition: color 0.5s;
}

.nonHomePageLogin > a:hover {
  color: rgba(0, 0, 0, 0.5);
  transition: color 0.5s;
}
</style>