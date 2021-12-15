<template>
  <div class="bannerDiv">
    <div class="bannerGrid" v-if="isHomePage">
      <div id="login">
        <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Employee Login</router-link>
        <div v-else>
          <router-link v-bind:to="{ name: 'pending-orders' }">Employee Portal</router-link>
          <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
        </div>
      </div>
    </div>
    <div class="bannerGrid" v-else-if="isLoginPage">
      <div id="login">
        <router-link v-bind:to="{ name: 'home' }">Back to Home</router-link>
      </div>
    </div>
    <div class="bannerGrid" v-else-if="isRegisterPage">
      <div id="register">
        <router-link v-bind:to="{ name: 'pending-orders' }">Back to Employee Portal</router-link>
      </div>
    </div>
    <div class="bannerGrid bannerImg" v-else>
      <div id="logo">
        <router-link v-bind:to="{ name: 'home' }"><h1>Salt & Pepperoni Pizzeria</h1></router-link>
      </div>
      <div id="login">
        <router-link v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Employee Login</router-link>
        <div v-else>
          <router-link v-bind:to="{ name: 'pending-orders' }">Employee Portal</router-link>
          <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
        </div>
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
    isRegisterPage() {
      if (this.$route.path == "/register") {
        return true;
      } else {
        return false;
      }
    }
  },
};
</script>

<style scoped>
.bannerGrid {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: "logo logo login";
  height: 96px;
  padding: 0 24px;
}

#login,
#register {
  grid-area: login;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  text-align: right;
}

#logo {
  grid-area: logo;
  display: flex;
  align-items: center;
}

#logo > a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.5s;
}

#login > a,
#login > div > a,
#register > a {
  color: #fff;
  text-decoration: none;
  font-size: 20px;
  font-weight: bold;
  transition: color 0.5s;
}

#login > a:hover,
#login > div > a:hover,
#register > a:hover {
  color: rgba(255, 255, 255, 0.5);
}

#login > div > a:nth-child(1) {
  margin-right: 24px;
}

.bannerImg {
  background: url(https://images.unsplash.com/photo-1630443876697-e0d2faac7b51?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 50% 60%;
}
</style>