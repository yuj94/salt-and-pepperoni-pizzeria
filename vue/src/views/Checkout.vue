<template>
  <div class="checkoutDiv">
    <banner class="banner"></banner>
    <checkout class="checkout"></checkout>
    <cart class="cart"></cart>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Checkout from "@/components/Checkout.vue";
import Cart from "@/components/Cart.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
    Checkout,
    Cart,
    Foot,
  },
};
</script>

<style>

.checkoutDiv {
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-template-areas: 
    "banner banner"
    "checkout cart"
    "footer footer";
  gap: 24px;
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

.foot {
  grid-area: footer;
}
</style>