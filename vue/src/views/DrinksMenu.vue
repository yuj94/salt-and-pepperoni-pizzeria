<template>
  <div class="drinksMenuDiv">
    <banner class="banner"></banner>
    <navigation class="navigation"></navigation>
    <menu-list class="menuList"></menu-list>
    <cart class="cart"></cart>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Navigation from "@/components/Navigation.vue";
import MenuList from "@/components/MenuList.vue";
import Cart from "@/components/Cart.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
    Navigation,
    MenuList,
    Cart,
  },
};
</script>

<style scoped>
.drinksMenuDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  gap: 10px;
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