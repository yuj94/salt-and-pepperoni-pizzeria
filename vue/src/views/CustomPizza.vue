<template>
  <div class="customPizzaDiv">
    <banner class="banner"></banner>
    <navigation class="navigation"></navigation>
    <ingredient-list class="ingredients"></ingredient-list>
    <cart class="cart"></cart>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Navigation from "@/components/Navigation.vue";
import IngredientList from "@/components/IngredientList.vue";
import Cart from "@/components/Cart.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
    Navigation,
    IngredientList,
    Cart,
  },
};
</script>

<style scoped>
.customPizzaDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: 
    "banner banner banner"
    "navigation menu-list cart"
    "footer footer footer";
}

.banner {
  grid-area: banner;
}

.navigation {
  grid-area: navigation;
  display: flexbox;
  flex-direction: column;
}

.menuList {
  grid-area: menu-list;
}

.cart {
  grid-area: cart;
}
</style>