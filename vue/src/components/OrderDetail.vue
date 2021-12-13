<template>
  <div class="orderDiv">
    <h2>Cart</h2>
    <ul class="order" v-for="item in this.$store.state.cart" v-bind:key="item.itemId">
      <li class="cartItem">
        {{item}} {{item}} - $ {{item}}
      </li>
    </ul>
    <ul class="order" v-for="item in this.$store.state.cart" v-bind:key="item.itemId">
      <li class="orderItem">
        Custom {{item}} Pizza - $ {{item}}
      </li>
    </ul>
    <h3>Total Price: ${{this.$store.state.cart}}</h3>
    
    <button type="button" v-on:click="goToRoute">{{(!isAtCheckout) ? 'Proceed to Checkout' : 'Back to Menu'}}</button>
  </div>
</template>

<script>
export default {
  name: "order",
  computed: {
    isAtCheckout() {
      return (this.$route.path == '/checkout') ? true: false;
    }
  },
  methods: {
    goToRoute() {
      if (!this.isAtCheckout) {
        this.$router.push({path: "checkout"});
        this.isAtCheckout = true;
      } else {
        this.$router.push({path: "pizzas"});
        this.isAtCheckout = false;
      }
    },
  }
};
</script>

<style scoped>
.orderDiv {
  border: solid 1px brown;
  align-content: top;
  text-align: center;
}

.order {
  list-style-type: none;
}
</style>