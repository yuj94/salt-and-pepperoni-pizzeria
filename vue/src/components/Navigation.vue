<template>
  <div class="navigationDiv">
    <router-link :to="{name: 'menu'}"><h1>Categories</h1> </router-link>
    <nav class="navigationNav" v-for="category in this.$store.state.categories" v-bind:key="category.name">
      <router-link :to="{name: `${category.toLowerCase()}-menu`}">
          <h2>{{category}}</h2>
        </router-link>
    </nav>
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
  display: flexbox;
  flex-direction: column;
  text-align: center;
}

.navigationNav {
  display: flex;
  flex-direction: column;
  margin: 10px 0;
}


</style>