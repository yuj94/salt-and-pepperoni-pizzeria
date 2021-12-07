<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>
    <h1>Pizza</h1>
    <menu-details v-for="pizza in this.$store.state.pizzaMenuItems" v-bind:key="pizza.pizzaId" v-bind:menuObj="pizza"></menu-details>
  </div>
</template>

<script>
import menuService from "@/services/MenuService.js";
import menuDetails from "./MenuDetails";

export default {
  name: "menu-list",
  components: {
    menuDetails,
  },
  data() {
    return {
      isLoading: true,
    }
  },
  methods: {
    getPizzas() {
      menuService.getAllMenuItems().then((response) => {
        this.$store.commit("SET_PIZZA_MENU_ITEMS", response.data);
        this.isLoading = false;
      });
    },
  },
  created() {
    this.getPizzas();
  },
};
</script>

<style scoped>
div {
  border: solid 1px green;
  margin: 30px 0;
}
</style>