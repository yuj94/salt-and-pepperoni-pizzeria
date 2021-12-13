<template>
  <div class="homeDiv">
    <banner></banner>
    <div class="homeText">
      <h1>Salt & Pepperoni Pizzeria</h1>
      <router-link v-bind:to="{ path: '/pizzas' }">VIEW MENU</router-link>
    </div>
    <foot></foot>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "home-view",
  components: {
    Banner,
    Foot,
  }
};
</script>

<style scoped>
.homeDiv {
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  background: url(https://images.unsplash.com/photo-1634629377376-6c6bae2d8bcf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1931&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  min-height: 100%;
}

.homeText {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.75);
  padding: 32px;
}

.homeText > h1 {
  color: #fff;
  font-size: 80px;
}

.homeText > a {
  text-decoration: none;
  color: #fff;
  background-color: #d20201;
  font-weight: bold;
  padding: 16px 24px;
  border-radius: 8px;
  margin-top: 32px;
  transition: all 0.5s;
}

.homeText > a:hover {
  background-color: rgba(210, 2, 1, 0.5);
}
</style>