<template>
  <div>
    <banner></banner>
    <navigation></navigation>
    <menu-list></menu-list>
    <cart></cart>
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

</style>