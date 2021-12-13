<template>
  <div class="menuDetailsDiv">
    <img id="itemImg" v-bind:src="menuObj.imageUrl" alt="Picture of menu item." />
    <h3 id="itemName">{{ menuObj.itemName }}</h3>
    <h3 id="itemPrice" v-if="!isPizza">$ {{ menuObj.price }}</h3>
    <div v-if="isPizza">
      <label for="pizzaSize">Choose a size: </label>
      <select name="pizzaSize" id="pizzaSize" v-model="pizzaSize">
        <option value="12">12"</option>
        <option value="16">16"</option>
        <option value="18">18"</option>
      </select>
      <h3 id="itemPrice">$ {{ pizzaPrice(menuObj.price) }}</h3>
    </div>
    <p id="itemDescription">{{ menuObj.itemDescription }}</p>
    <button type="button" class="cartButton" v-on:click="addItemToCart('My item')">Add to Cart</button>
  </div>
</template>

<script>
export default {
  name: "menu-details",
  data() {
    return {
      pizzaSize: 12,
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
        return originalPrice;
      } else if (this.pizzaSize == 16) {
        return originalPrice + 4;
      } else {
        return originalPrice + 6;
      }
    },
    addItemToCart(item) {
      this.$store.commit("ADD_MENU_ITEM_TO_CART", item);
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
    "img description cartButton";
  column-gap: 16px;
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
}

#itemPrice {
  grid-area: price;
  align-items: center;
}

#itemDescription {
  grid-area: description;
}

.cartButton {
  grid-area: cartButton;
  justify-content: center;
}
</style>