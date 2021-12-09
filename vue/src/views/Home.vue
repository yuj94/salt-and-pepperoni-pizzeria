<template>
  <div class="homeDiv">
    <banner class="banner"></banner>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
  },
};
</script>

<style scoped>
.homeDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  gap: 10px;
  grid-template-areas: 
    "banner banner cart";
}

.banner {
  grid-area: banner;
}

.cart {
  grid-area: cart;
}
</style>