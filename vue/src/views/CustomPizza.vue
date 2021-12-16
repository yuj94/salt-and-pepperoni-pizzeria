<template>
  <div class="customPizzaDiv">
    <banner class="banner"></banner>
    <navigation class="navigation"></navigation>
    <ingredient-list class="ingredients"></ingredient-list>
    <cart class="cart"></cart>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Navigation from "@/components/Navigation.vue";
import IngredientList from "@/components/IngredientList.vue";
import Cart from "@/components/Cart.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
    Navigation,
    IngredientList,
    Cart,
    Foot,
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
  gap: 24px;
}

.banner {
  grid-area: banner;
}

.navigation {
  grid-area: navigation;
}

.menuList {
  grid-area: menu-list;
}

.cart {
  grid-area: cart;
}

.foot {
  grid-area: footer;
}
</style>