<template>
  <div>
    <div class="categoryHeaderDiv">
      <img v-bind:src="menuObj.imageUrl" alt="Picture of menu item." />
      <h2 id="itemName">{{ menuObj.itemName }}</h2>

      <h2 id="itemPrice" v-if="!isPizza">${{ menuObj.price }}</h2>
      <div v-if="isPizza">
        <label for="pizzaSize">Choose a size:</label>
        <select name="pizzaSize" id="pizzaSize" v-model="pizzaSize">
          <option value=12>12"</option>
          <option value=16>16"</option>
          <option value=18>18"</option>
        </select>
        <h2 id="itemPrice">${{ pizzaPrice(menuObj.price) }}</h2>
      </div>
      <form action="">
        <button type="button" class="cartButton">Add to Cart</button>
      </form>
      <p id="itemDescription">{{ menuObj.itemDescription }}</p>
    </div>
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
  }
};
</script>

<style scoped>
.categoryHeaderDiv {
  margin: none;
  display: grid;
  grid-template-columns: 1fr 4fr 1fr;
  grid-template-areas:
    "img name price"
    "img description cartButton";
}

img {
  grid-area: img;
  align-content: center;
  justify-content: center;
  max-width: 100%;
}

.cartButton {
  grid-area: cartButton;
  justify-content: center;
}

#itemPrice {
  grid-area: price;
  align-items: center;
}

#itemName {
  grid-area: name;
}

#itemDescription {
  grid-area: description;
}
</style>