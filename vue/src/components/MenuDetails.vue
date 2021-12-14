<template>
  <div class="menuDetailsDiv">
    <img id="itemImg" v-bind:src="menuObj.imageUrl" alt="Picture of menu item." />
    <div id="itemName" v-if="isPizza">
      <h3>{{ menuObj.itemName }}</h3>
      <div id="pizzaSize">
        <label for="pizzaSize">Choose a size: </label>
        <select name="pizzaSize" id="pizzaSize" v-model="pizzaSize">
            <option value=12>12"</option>
            <option value=16>16"</option>
            <option value=18>18"</option>
        </select>
      </div>
    </div>
    <h3 id="itemName" v-if="!isPizza">{{ menuObj.itemName }}</h3>
    <h3 id="itemPrice">$ {{ Number(pizzaPrice(menuObj.price)).toFixed(2) }}</h3>
    <p id="itemDescription">{{ menuObj.itemDescription }}</p>
    <button type="button" id="itemCart" v-on:click="addItemToCart(menuObj)">Add to Cart</button>
  </div>
</template>

<script>
export default {
  name: "menu-details",
  data() {
    return {
      pizzaSize: "12",
      itemToAddToCart: {
      },
    };
  },
  props: ["menuObj"],
  computed: {
    isPizza() {
      if (this.$route.params.category.substring(0, this.$route.params.category.length - 1) == "pizza") {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    pizzaPrice(originalPrice) {
      if (this.pizzaSize == 12) {
        return Number(originalPrice).toFixed(2);
      } else if (this.pizzaSize == 16) {
        return Number(originalPrice + 4).toFixed(2);
      } else {
        return Number(originalPrice + 6).toFixed(2);
      }
    },
    addItemToCart(item) {
      let filteredCartArray = this.$store.state.cart.filter((e) => { return e.itemId === item.itemId});
      let filteredPizzaArray = this.$store.state.cart.filter((e) => { return e.itemId === item.itemId && e.itemSize == this.pizzaSize});
      if (filteredPizzaArray.length >= 1 && item.Category === "Pizza") {
        this.$store.commit("UPDATE_PIZZA_QUANTITY", item.itemId, this.pizzaSize);
      } else if (filteredCartArray.length >= 1) {
        this.$store.commit("UPDATE_MENU_ITEM_QUANTITY", item.itemId);
      } else {
        if (this.isPizza) {
          this.itemToAddToCart.price = this.pizzaPrice(item.price);
          this.itemToAddToCart.itemSize = this.pizzaSize;
        } else {
          this.itemToAddToCart.price = item.price;
          this.itemToAddToCart.itemSize = "0";
        }
        this.itemToAddToCart.menuItemCategory = this.$route.params.category.substring(0, this.$route.params.category.length - 1);
        this.itemToAddToCart.itemName = item.itemName;
        this.itemToAddToCart.itemId = item.itemId;
        this.itemToAddToCart.itemCategory = "Menu";
        this.itemToAddToCart.quantity = 1;
        this.itemToAddToCart.cartItemId = this.$store.state.currentCartItemId;
        this.$store.commit("ADD_MENU_ITEM_TO_CART", this.itemToAddToCart);
        this.$store.state.currentCartItemId ++;
      }

    },
  },
};
</script>

<style scoped>
.menuDetailsDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas:
    "img name price"
    "img description cart";
  gap: 16px;
  background-color: #fff;
  padding: 16px;
  border-radius: 8px;
}

#itemImg {
  grid-area: img;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

#itemName {
  grid-area: name;
  display: flex;
  justify-content: space-between;
}

#itemPrice {
  grid-area: price;
  text-align: center;
}

#itemDescription {
  grid-area: description;
}

#itemCart {
  grid-area: cart;
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

#itemCart:hover {
  background-color: rgba(210, 2, 1, 0.5);
}
</style>