<template>
  <div class="pizza-items">
    <div v-for="item in this.$store.state.menuItems" v-bind:key="item.itemId">
      <h1>{{item.itemCategory}}</h1>
    </div>
   
    <div v-for="item in this.$store.state.menuItems" v-bind:key="item.itemId"> 
      <h2>{{item.itemName}}</h2>
      <h2>{{item.price}}</h2>
      <p>{{item.itemDescription}}</p>
    </div>
  </div>
</template>

<script>
import menuService from '@/services/MenuService.js';

export default {
    name: 'pizza-items',
    methods: {
        getPizzaItems() {
            menuService.getPizzaItems().then((response) => {
            this.$store.commit("SET_MENU_ITEMS", response.data);
        })
        }
    },
    created() {
       this.getPizzaItems();

    }
};
</script>

<style>

</style>