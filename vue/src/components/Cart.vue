<template>
  <div class="cartDiv">
    <h2>Cart</h2>
    <ul class="customerCart" v-for="item in this.$store.state.cart" v-bind:key="item.cartItemId">
      <li class="cartItem" v-if="isMenuItem(item) && !isPizza(item)">Qty: {{item.quantity}} - {{ item.itemName }} - ${{ Number(item.price).toFixed(2) }} each</li>
      <li class="cartItem" v-else-if="isPizza(item)">Qty: {{item.quantity}} - {{ item.itemSize }}" {{ item.itemName }} - ${{ Number(item.price).toFixed(2) }} each</li>
      <li class="cartItem" v-else>Qty: {{item.quantity}} - Custom {{ item.itemSize }}" Pizza - ${{ Number(item.price).toFixed(2) }} each</li>
      <button v-on:click="removeItem(item)">Delete Item</button>
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
        totalPrice += (item.price * item.quantity);
      });
      return Number(totalPrice).toFixed(2);
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
    removeItem(item) {
      this.$store.commit("REMOVE_ITEM_FROM_CART", item);
    },
    isMenuItem(item) {
      if (item.itemCategory == 'Menu') {
        return true; 
        } else {
          return false;
        }
    },
    isPizza(item) {
      if (item.menuItemCategory == "pizza") {
        return true;
      } else {
        return false;
      }
    }
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