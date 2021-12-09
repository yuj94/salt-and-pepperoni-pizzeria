<template>
  <div>
    <nav>
      <h2>Categories</h2>
      <p>Appetizers</p>
      <p>Desserts</p>
      <p>Drinks</p>
      <p>Salads</p>
      <p>Pizzas</p>
    </nav>
  </div>
</template>

<script>
export default {
  name: "navigation",
};
</script>

<style scoped>
div {
  border: solid 1px red;
}

nav {
  display: flex;
  flex-direction: column;
  /* align-content: justify; */
  align-items: center;
  justify-content: top;
}


</style>