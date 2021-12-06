<template>
  <div class="menu-items">
    <h1>Pizza</h1>
    <div
      v-for="item in this.$store.state.pizzaMenuItems"
      v-bind:key="item.pizzaId"> {{item.pizzaName}} 
    </div>
  </div>
</template>

<script>
import menuService from '@/services/MenuService.js';

export default {
    name: 'menu-items',
    methods: {
        getPizzas() {
            menuService.getMenuItems().then((response) => {
            this.$store.commit("SET_PIZZA_MENU_ITEMS", response);
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