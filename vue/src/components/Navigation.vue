<template>
  <div class="navigationDiv">
    <h2>Categories</h2>
    <nav class="navigationNav" v-for="category in this.$store.state.categories" v-bind:key="category.name">
      <router-link :to="{path: `/${category.substring(0, category.length - 1).toLowerCase()}s`}">
        <h3>{{ category }}</h3>
      </router-link>
    </nav>
    <router-link :to="{ path: '/custom-pizza' }">
      <h3>Custom Pizza</h3>
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
  text-align: center;
  padding: 0 24px;
}

.navigationDiv > h2 {
  background-color: #d20201;
  border-radius: 8px;
  color: #fff;
  padding: 16px;
}

.navigationNav > a {
  text-decoration: none;
  color: #000;
  transition: color 0.5s;
}

.navigationNav > a:hover {
  color: #d20201;
}

.navigationNav > a > h3 {
  padding: 16px;
  margin: 16px 0;
}

.navigationDiv > a {
  text-decoration: none;
  color: #000;
  transition: color 0.5s;
}

.navigationDiv > a:hover {
  color: #d20201;
}

.navigationDiv > a > h3 {
  padding: 16px;
}

.router-link-exact-active {
  color: #d20201 !important;
}

.router-link-exact-active > h3 {
  background-color: #fff;
  border-radius: 8px;
}
</style>