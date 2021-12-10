<template>
  <div class="cartDiv">
    <h2>Cart</h2>
    <ul class="customerCart">
      <li class="cartItem">
        Items will go here with price and a button to adjust quantity/delete.
      </li>
    </ul>
    <button type="button">Proceed to Checkout</button>
  </div>
</template>

<script>
export default {
  name: "cart",
};
</script>

<style scoped>
.cartDiv {
  border: solid 1px brown;
  align-content: top;
  text-align: center;
}

.customerCart {
  list-style-type: none;
}
</style>