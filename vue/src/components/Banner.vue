<template>
  <div class="bannerDiv">
    <div class="bannerGrid" v-if="isHomePage">
      <div id="login">
        <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Employee Login</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-else>Logout</router-link>
      </div>
    </div>
    <div class="bannerGrid" v-else-if="isLoginPage">
      <div id="login">
        <router-link v-bind:to="{ name: 'home' }">Back to Home</router-link>
      </div>
    </div>
    <div class="bannerGrid bannerImg" v-else>
      <div id="logo">
        <router-link v-bind:to="{ name: 'home' }"><h1>Salt & Pepperoni Pizzeria</h1></router-link>
      </div>
      <div id="login">
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
    isLoginPage() {
      if (this.$route.path == "/login") {
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
  color: #fff;
}

#login > a {
  color: #fff;
  transition: color 0.5s;
}

#login > a:hover {
  color: rgba(255, 255, 255, 0.5);
}

.bannerImg {
  background: url(https://images.unsplash.com/photo-1630443876697-e0d2faac7b51?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 50% 60%;
}
</style>