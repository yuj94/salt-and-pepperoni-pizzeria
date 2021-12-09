<template>
  <div>
    <!-- router-link -->
    <div class="menu-item" v-bind:key="menuObj.itemId">
      <h2>{{ menuObj.itemName }}</h2>
      <h2>${{ menuObj.price }}</h2>
      <p>{{ menuObj.itemDescription }}</p>
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
.menu-item {
  border: solid 1px navy;
  margin: auto;
}
</style>