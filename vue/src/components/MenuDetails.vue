<template>
  <div>
    <!-- router-link -->
    <div v-bind:key="menuObj.pizzaId">
      <h2>{{ menuObj.pizzaName }}</h2>
      <h2>{{ menuObj.price }}</h2>
      <p>{{ menuObj.pizzaDescription }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "menu-details",
  props: ["menuObj"],
};
</script>

<style scoped>
div {
  border: solid 1px navy;
  margin: 10px 0;
}
</style>