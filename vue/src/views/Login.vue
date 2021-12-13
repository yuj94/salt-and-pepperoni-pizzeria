<template>
  <div id="loginDiv" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Employee Login</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">Invalid username and password!</div>
      <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input type="text" id="username" class="form-control" placeholder="Username" v-model="user.username" required autofocus/>
      <label for="password" class="sr-only">Password</label>
      <input type="password" id="password" class="form-control" placeholder="Password" v-model="user.password" required/>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/employee-portal/pending-orders");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#loginDiv {
  display: flex;
  justify-content: center;
  align-items: center;
  background: url(https://images.unsplash.com/photo-1634629377376-6c6bae2d8bcf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1931&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  min-height: 100%;
}

.form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.75);
  color: #fff;
  padding: 32px;
  border-radius: 8px;
}

.form-signin > h1 {
  font-size: 40px;
}

.form-signin > label {
  padding: 8px;
}

.form-signin > input {
  border: none;
  padding: 16px;
  border-radius: 8px;
}

.form-signin > button {
  border: none;
  padding: 16px 24px;
  border-radius: 8px;
  background-color: #d20201;
  color: #fff;
  margin-top: 32px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.5s;
}

.form-signin > button:hover {
  background-color: rgba(210, 2, 1, 0.5);
}
</style>