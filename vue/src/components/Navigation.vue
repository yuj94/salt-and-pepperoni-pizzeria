<template>
  <div class="navigationDiv">
    <h1>Categories</h1>
    <nav class="navigationNav" v-for="category in this.$store.state.categories" v-bind:key="category.name">
      <router-link :to="{path: `/${category.substring(0, category.length - 1).toLowerCase()}s`}">
          <h2>{{category}}</h2>
        </router-link>
    </nav>
    <router-link :to="{path: '/custom-pizza'}">
          <h2>Custom Pizza</h2>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "navigation",
};
</script>

<style scoped>
.navigationDiv {
  border: solid 1px red;
  color: #d20201;
  text-align: center;
}


</style>