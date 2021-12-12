<template>
  <div class="homeDiv">
    <banner></banner>
    <div class="homeText">
      <h1>Salt & Pepperoni Pizzeria</h1>
      <router-link v-bind:to="{ path: '/pizzas' }">Menu</router-link>
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
  },
  methods: {
    created() {
      this.$store.state.isLoading = false;
    },
  },
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
}
</style>