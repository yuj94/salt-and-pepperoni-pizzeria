<template>
  <div class="ingredientListDiv">
    <div class="customPizzaHeader">
      <h2>Create Your Own</h2>
      <div class="sizeATC">
        <div>
          <label for="pizzaSize">Choose a pizza size:</label>&nbsp;
          <select name="pizzaSize" id="pizzaSize" v-model="pizzaSize">
            <option value="12">12"</option>
            <option value="16">16"</option>
            <option value="18">18"</option>
          </select>
        </div>
        <button id="customCart" v-on:click="addToCart">Add to Cart</button>
      </div>
    </div>
    <div class="pizzaDescription">
      <p>For our <span class="bold">{{ this.pizzaSize }}"</span> custom pizzas, our prices start at <span class="bold">${{ this.basePrice }}</span> for the dough plus the cost of the ingredients selected.</p>
      <p>Your current custom pizza costs: <span class="bold">${{this.computedPrice}}</span>.</p>
      <p> You can select up to <span class="bold">{{ this.toppingsRemaining }}</span> more ingredients for your custom pizza.</p>
    </div>
    <div class="ingredientList">
      <div>
        <h3 id="meatId">Meats</h3>
        <div class="meats ingredientCategory" v-for="ingredient in meats" v-bind:key="ingredient.ingredientId">
          <p id="ingredientName"> {{ ingredient.ingredientName }}</p>
          <p id="ingredientPrice">Price: ${{ ingredient.price.toFixed(2) }}</p>
          <select name="ingredientQuantity" id="ingredientQuantity" v-bind="ingredients">
            <option value="0">None</option>
            <option value="1">Regular</option>
            <option value="2">Double</option>
          </select>
        </div>
      </div>
      <div>
        <h3 id="veggieId">Veggies</h3>
        <div class="veggies ingredientCategory" v-for="ingredient in veggies" v-bind:key="ingredient.ingredientId">
          <input type="checkbox" id="ingredientId" name="ingredientName" value="ingredient" v-bind="ingredients"/>
          <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
          <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
          &nbsp;
          <select name="ingredientQuantity" id="ingredientQuantity" v-bind="ingredients">
            <option value="0">None</option>
            <option value="1">Regular</option>
            <option value="2">Double</option>
          </select>
        </div>
      </div>
      <div>
        <h3 id="sauceId">Sauces</h3>
        <div class="sauces ingredientCategory" v-for="ingredient in sauces" v-bind:key="ingredient.ingredientId">
          <input type="checkbox" id="ingredientId" name="ingredientName" value="ingredient" v-bind="ingredients"/>
          <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
          <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
          &nbsp;
          <select name="ingredientQuantity" id="ingredientQuantity" v-bind="ingredients">
            <option value="0">None</option>
            <option value="1">Regular</option>
            <option value="2">Double</option>
          </select>
        </div>
      </div>
      <div>
        <h3 id="cheeseId">Cheeses</h3>
        <div class="cheeses ingredientCategory" v-for="ingredient in cheeses" v-bind:key="ingredient.ingredientId">
          <input type="checkbox" id="ingredientId" name="ingredientName" value="ingredient" v-bind="ingredients"/>
          <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
          <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
          &nbsp;
          <select name="ingredientQuantity" id="ingredientQuantity" v-bind="ingredients">
            <option value="0">None</option>
            <option value="1">Regular</option>
            <option value="2">Double</option>
          </select>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import menuService from "@/services/MenuService.js";

export default {
  name: "ingredient-list",
  data() {
    return {
      pizzaSize: 12,
      customPizza: {
        ingredients: this.ingredients,
        size: this.pizzaSize,
        price: this.computedPrice,
      },
      ingredients: [],
      quantities: [],
      toppingsRemaining: 9,
    };
  },
  computed: {
    computedPrice() {
      return this.basePrice;
    },
    sauces() {
      return this.$store.state.ingredients.filter((ingredient) => {
        return ingredient.ingredientType == "Sauce";
      });
    },
    meats() {
      return this.$store.state.ingredients.filter((ingredient) => {
        return ingredient.ingredientType == "Meat";
      });
    },
    veggies() {
      return this.$store.state.ingredients.filter((ingredient) => {
        return ingredient.ingredientType == "Veggies";
      });
    },
    cheeses() {
      return this.$store.state.ingredients.filter((ingredient) => {
        return ingredient.ingredientType == "Cheese";
      });
    },
    basePrice() {
      let basePrice = 0;
      let doughIngredient = [];
      doughIngredient = this.$store.state.ingredients.filter((ingredient) => {
        return ingredient.ingredientType == "Dough";
      });
      doughIngredient.forEach((element) => {
        basePrice += element.price;
      });
      if (this.pizzaSize == 16) {
        basePrice += 4;
      } else if (this.pizzaSize == 18) {
        basePrice += 6;
      }
      return basePrice;
    },
  },
  methods: {
    getIngredients() {
      menuService.getPizzaIngredients().then((response) => {
        this.$store.commit("SET_INGREDIENTS", response.data);
      });
    },
    adjustToppingsRemaining() {},
    addToCart() {
      this.$store.state.cart.push(this.customPizza);
    },
  },
  created() {
    this.getIngredients();
  },
};
</script>

<style scoped>
.customPizzaHeader > h2 {
  background-color: #d20201;
  color: #fff;
  border-radius: 8px;
  text-align: center;
  padding: 16px;
}

.sizeATC {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#customCart {
  border: none;
  padding: 16px 24px;
  border-radius: 8px;
  margin: 16px 0;
  background-color: #d20201;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.5s;
}

#customCart:hover {
  background-color: rgba(210, 2, 1, 0.5);
}

.pizzaDescription > p {
  margin: 16px 0;
}

.pizzaDescription > p > .bold {
  font-weight: bold;
}

.ingredientList {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "meatId veggieId sauceId cheeseId"
    "meat veggies sauce cheese";
  grid-gap: 16px;
}

#meatId {
  grid-area: meatId;
}

#veggieId {
  grid-area: veggieId;
}

#sauceId {
  grid-area: sauceId;
}

#cheeseId {
  grid-area: cheeseId;
}

.meats {
  grid-area: meat;
}

.veggies {
  grid-area: veggies;
}

.sauces {
  grid-area: sauce;
}

.cheeses {
  grid-area: cheese;
}

.ingredientCategory {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  margin-top: 16px;
  border-radius: 8px;
  padding: 16px;
 }

.ingredientCategory > label,
#ingredientName,
#ingredientPrice,
#ingredientQuantity {
  margin: 8px 0;
}

#ingredientQuantity {
  width: 80px;
}
</style>