<template>
  <div class="menuCategoryDiv">
      <div class="categoryHeaderDiv" v-for="category in this.$store.state.categories" v-bind:key="category.name">
        <h2>{{category}}</h2>
            <div v-for="item in this.$store.state.menuItems" v-bind:key="item.itemId">
            <menu-details class="menuDetails" v-bind:menuObj="item"></menu-details>
            </div>
      </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>