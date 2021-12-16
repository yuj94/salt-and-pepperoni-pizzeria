<template>
  <div class="checkoutDiv">
    <banner class="banner"></banner>
    <navigation class="navigation"></navigation>
    <checkout class="checkout"></checkout>
    <cart class="cart"></cart>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import Banner from "@/components/Banner.vue";
import Navigation from "@/components/Navigation.vue";
import Checkout from "@/components/Checkout.vue";
import Cart from "@/components/Cart.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "menu-view",
  components: {
    Banner,
    Navigation,
    Checkout,
    Cart,
    Foot,
  },
};
</script>

<style scoped>
.checkoutDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas:
    "banner banner banner"
    "navigation checkout cart"
    "footer footer footer";
  gap: 24px;
}

.banner {
  grid-area: banner;
}

.navigation {
  grid-area: navigation;
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