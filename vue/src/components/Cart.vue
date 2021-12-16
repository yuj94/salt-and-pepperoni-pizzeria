<template>
  <div class="cartDiv">
    <h2>Cart</h2>
    <div class="emptyCart" v-if="this.$store.state.cart.length == 0"><p>Your cart is empty.</p></div>
    <div v-else>
      <div class="customerCart">
        <ul class="cartList" v-for="item in this.$store.state.cart" v-bind:key="item.cartItemId">
          <li class="cartItem" v-if="isMenuItem(item) && !isMenuPizza(item)">Qty: {{item.orderQuantity}} - {{ item.itemName }} - ${{ Number(item.price).toFixed(2) }} each</li>
          <li class="cartItem" v-else-if="isMenuPizza(item)">Qty: {{item.orderQuantity}} - {{ item.itemSize }}" {{ item.itemName }} - ${{ Number(item.price).toFixed(2) }} each</li>
          <li class="cartItem" v-else>Qty: {{item.orderQuantity}} - Custom {{ item.itemSize }}" Pizza - ${{ Number(item.price).toFixed(2) }}</li>
          <button v-on:click="removeItem(item)">Remove</button>
        </ul>
      </div>
      <h3 class="cartPrice">Total Price: ${{ this.totalPrice }}</h3>
      <button type="button" id="buttonCart" v-on:click="goToRoute" v-if="!isAtCheckout">
        Proceed to Checkout
      </button>
    </div>
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
        totalPrice += (item.price * item.orderQuantity);
      });
      return Number(totalPrice).toFixed(2);
    },
    // isCartEmpty() {
    //   if (this.$store.state.cart.length )
    // },
  },
  methods: {
    goToRoute() {
      if (!this.isAtCheckout) {
        this.$router.push({ path: "checkout" });
        this.isAtCheckout = true;
      } else {
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
    isMenuPizza(item) {
      if (item.itemCategory == "Pizza" && item.itemType == "Menu") {
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
  padding-right: 24px;
}

.cartDiv > h2 {
  background-color: #d20201;
  border-radius: 8px;
  color: #fff;
  padding: 16px;
}

.emptyCart {
  background-color: #fff;
  margin: 16px 0;
  padding: 16px;
  border-radius: 8px;
}

.customerCart {
  background-color: #fff;
  margin: 16px 0;
  padding: 16px;
  border-radius: 8px;
}

.cartList {
  list-style-type: none;
}

.cartItem {
  margin: 16px 0;
}

.cartList > button {
  border: none;
  padding: 8px 24px;
  border-radius: 8px;
  background-color: #d20201;
  color: #fff;
  font-weight: bold;
  margin-bottom: 16px;
  cursor: pointer;
  transition: background-color 0.5s;
}

.cartList > button:hover {
  background-color: rgba(210, 2, 1, 0.5);
}

.cartPrice {
  margin: 16px 0;
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