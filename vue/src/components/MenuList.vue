<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>

    <div class="loaded" v-else>
      <h1 class="categoryHeader">{{this.$route.params.category.toUpperCase()}}</h1>
      <div v-for="item in currentCategoryItems" v-bind:key="item.itemId">
        <div class="allMenuItems">
          <menu-details
            class="menuDetails"
            v-bind:menuObj="item"
          ></menu-details>
        </div>
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
  data() {
    return {
      isLoading: true,
    };
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
  margin: 0 0 10px 0;
}

.categoryHeader {
  color: #d20201;
  text-align: center;
}
</style>