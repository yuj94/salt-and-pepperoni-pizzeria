<template>
  <div class="menuListDiv">
    <h2 class="categoryHeader">{{ this.$route.params.category.toUpperCase() }}</h2>
    <div v-for="item in currentCategoryItems" v-bind:key="item.itemId">
      <div class="allMenuItems">
        <menu-details class="menuDetails" v-bind:menuObj="item"></menu-details>
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
  computed: {
    currentCategoryItems() {
      return this.$store.state.menuItems.filter((item) => {
        return item.itemCategory.toLowerCase() == this.$route.params.category.substring(0, this.$route.params.category.length - 1);
      })
    },
  },
  methods: {
    getMenuItems() {
      menuService.getAllMenuItems().then((response) => {
        this.$store.commit("SET_MENU_ITEMS", response.data);
      });
    },
    // capitalizeFirstLetter(string) {
    //   return string.charAt(0).toUpperCase() + string.slice(1);
    // },
  },
  created() {
    this.getMenuItems();
  },
};
</script>

<style scoped>
.menuListDiv > h2 {
  background-color: #d20201;
  border-radius: 8px;
  color: #fff;
  padding: 16px;
}
.allMenuItems {
  border: solid 1px green;
  margin: 0 0 10px 0;
}

.categoryHeader {
  color: #d20201;
  text-align: center;
}
</style>