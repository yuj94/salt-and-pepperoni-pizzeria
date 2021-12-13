<template>
  <div class="cartDiv">
    <h2>Cart</h2>
    <ul class="customerCart" v-for="item in this.$store.state.cart" v-bind:key="item.itemId">
      <li class="cartItem">{{ item }} {{ item }} - $ {{ item }}</li>
      <button>Delete Item</button>
    </ul>
    <ul class="customerCart" v-for="item in this.$store.state.cart" v-bind:key="item.itemId">
      <li class="cartItem">Custom {{ item }} Pizza - $ {{ item }}</li>
      <button>Delete Item</button>
    </ul>
    <h3>Total Price: ${{ this.totalPrice }}</h3>
    <button type="button" id="buttonCart" v-on:click="goToRoute">
      {{ !isAtCheckout ? "Proceed to Checkout" : "Back to Menu" }}
    </button>
  </div>
</template>

<script>
export default {
  name: "cart",
  computed: {
    isAtCheckout() {
      return this.$route.path == "/checkout" ? true : false;
    },
    totalPrice() {
      let totalPrice = 0;
      this.$store.state.cart.forEach((item) => {
        totalPrice += item.price;
      });
      return totalPrice;
    }
  },
  methods: {
    goToRoute() {
      if (!this.isAtCheckout) {
        this.$router.push({ path: "checkout" });
        this.isAtCheckout = true;
      } else {
        this.$router.push({ path: "pizzas" });
        this.isAtCheckout = false;
      }
    },
  },
};
</script>

<style scoped>
.cartDiv {
  text-align: center;
  padding: 0 24px;
}

.cartDiv > h2 {
  background-color: #d20201;
  border-radius: 8px;
  color: #fff;
  padding: 16px;
}

.customerCart {
  list-style-type: none;
}

#buttonCart {
  border: none;
  padding: 16px 24px;
  border-radius: 8px;
  margin: auto;
  background-color: #d20201;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.5s;
}

#buttonCart:hover {
  background-color: rgba(210, 2, 1, 0.5);
}
</style>