<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>
   <!-- <div v-for="category in this.itemCategories" v-bind:key="category.name">
      <h1>{{category.itemCategories}}</h1>
    </div> -->
  <div>
    <menu-details v-for="item in this.$store.state.menuItems" v-bind:key="item.itemId" v-bind:menuObj="item"></menu-details>
  </div>
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
      // itemCategories: this.$store.state.menuItems.itemCategories.removeD,
    }
  },
  methods: {
    getMenuItems() {
      menuService.getAllMenuItems().then((response) => {
        this.$store.commit("SET_MENU_ITEMS", response.data);
        this.isLoading = false;
      });
    },
  },
  created() {
    this.getMenuItems();
  },
};
</script>

<style scoped>
div {
  border: solid 1px green;
  margin: 30px 0;
}
</style>