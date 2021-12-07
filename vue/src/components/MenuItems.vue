<template>
  <div class="menu-items">
    <h1>Pizza</h1>
    <div v-for="item in this.$store.state.pizzaMenuItems" v-bind:key="item.pizzaId"> 
      <h2>{{item.pizzaName}}</h2>
      <h2>{{item.price}}</h2>
      <p>{{item.pizzaDescription}}</p>
    </div>
  </div>
</template>

<script>
import menuService from '@/services/MenuService.js';

export default {
    name: 'menu-items',
    methods: {
        getPizzas() {
            menuService.getAllMenuItems().then((response) => {
            this.$store.commit("SET_PIZZA_MENU_ITEMS", response.data);
        })
        }
    },
    created() {
       this.getPizzas();

    }
};
</script>

<style>
</style>