<template>
  <div class="checkoutDiv">
    <banner class="banner"></banner>
    <checkout class="checkout"></checkout>
    <cart class="cart"></cart>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Checkout from "@/components/Checkout.vue";
import Cart from "@/components/Cart.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
    Checkout,
    Cart,
  },
};
</script>

<style>

.checkoutDiv {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 10px;
  grid-template-areas: 
    "banner banner"
    "checkout cart";
}

.banner {
  grid-area: banner;
}

.checkout {
    grid-area: checkout;
}

.cart {
  grid-area: cart;
}
</style>