<template>
  <div id="registerDiv" class="text-center">
    <banner></banner>
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Register an Employee</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">{{ registrationErrorMsg }}</div>
      <label for="username" class="sr-only">Username</label>
      <input type="text" id="username" class="form-control" placeholder="Username" v-model="user.username" required autofocus />
      <label for="password" class="sr-only">Password</label>
      <input type="password" id="password" class="form-control" placeholder="Password" v-model="user.password" required />
      <input type="password" id="confirmPassword" class="form-control" placeholder="Confirm Password" v-model="user.confirmPassword" required />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Create Account</button>
    </form>
    <foot></foot>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Foot from "@/components/Foot.vue";
import authService from "../services/AuthService";

export default {
  name: "register",
  components: {
    Banner,
    Foot,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
#registerDiv {
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  background: url(https://images.unsplash.com/photo-1634629377376-6c6bae2d8bcf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1931&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  min-height: 100%;
}

.form-register {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.75);
  color: #fff;
  padding: 32px;
}

.form-register > h1 {
  font-size: 40px;
}

.form-register > .alert-danger {
  padding-top: 16px;
}

.form-register > label {
  padding: 16px;
}

.form-register > input {
  border: none;
  padding: 16px;
  border-radius: 8px;
}

#confirmPassword {
  margin-top: 16px;
}

.form-register > a {
  color: #fff;
  padding-top: 16px;
  transition: color 0.5s;
}

.form-register > a:hover {
  color: rgba(255, 255, 255, 0.5);
}

.form-register > button {
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

.form-register > button:hover {
  background-color: rgba(210, 2, 1, 0.5);
}
</style>
