<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>
    <div class="loaded" v-for="item in this.$store.state.menuItems" v-bind:key="item.itemId" v-else>
      <div class="allMenuItems">
        <menu-details class="menuDetails" v-bind:menuObj="item"></menu-details>
      </div>
      <div class="categoryMenuItems">

      </div>
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
.allMenuItems {
  border: solid 1px green;
  margin: 30px 0;
}

</style>