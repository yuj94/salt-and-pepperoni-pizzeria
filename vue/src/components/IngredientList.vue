<template>
  <div class="ingredientListDiv">
    <h2>Create Your Own</h2>
    <div class="ingredientListDesc">
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
      <div class="pizzaDescription">
        <p>
          For our <span class="bold">{{ this.pizzaSize }}"</span> custom pizzas, our prices start at <span class="bold">${{ this.basePrice }}</span> for the dough plus the cost of the ingredients selected.
        </p>
        <p>
          Your current custom pizza costs: <span class="bold">${{ this.computedPrice }}</span>.
        </p>
        <p>
          You can select up to <span class="bold">{{ this.toppingsRemaining }}</span> more ingredients for your custom pizza.
        </p>
      </div>
    </div>
    <div class="ingredientList">
      <div>
        <h3 id="sauceId">Sauces</h3>
        <div class="sauces ingredientCategory" v-for="ingredient in sauces" v-bind:key="ingredient.ingredientId" v-bind:class="{ ingredientChecked: ingredient.checked }" v-show="toppingsRemaining > 0 || ingredient.checked">
          <div class="ingredientCheckbox">
            <input type="checkbox" :id="ingredient" :name="ingredient.ingredientName" :value="ingredient" v-model="ingredientsToAdd" v-bind:checked="ingredient.checked" v-on:change="checkedIngredients(ingredient)"/>
            <label :for="ingredient"></label>
          </div>
          <div>
            <p id="ingredientName"><span class="bold">{{ ingredient.ingredientName }}</span></p>
            <p id="ingredientPrice">Price: ${{ Number(ingredient.price).toFixed(2) }}</p>
          </div>
        </div>
      </div>
      <div>
        <h3 id="cheeseId">Cheeses</h3>
        <div class="cheeses ingredientCategory" v-for="ingredient in cheeses" v-bind:key="ingredient.ingredientId" v-bind:class="{ ingredientChecked: ingredient.checked }" v-show="toppingsRemaining > 0 || ingredient.checked">
          <div class="ingredientCheckbox">
            <input type="checkbox" :id="ingredient" :name="ingredient.ingredientName" :value="ingredient" v-model="ingredientsToAdd" v-bind:checked="ingredient.checked" v-on:change="checkedIngredients(ingredient)"/>
            <label :for="ingredient"></label>
          </div>
          <div>
            <p id="ingredientName"><span class="bold">{{ ingredient.ingredientName }}</span></p>
            <p id="ingredientPrice">Price: ${{ Number(ingredient.price).toFixed(2) }}</p>
          </div>
        </div>
      </div>
      <div>
        <h3 id="meatId">Meats</h3>
        <div class="meats ingredientCategory" v-for="ingredient in meats" v-bind:key="ingredient.ingredientId" v-bind:class="{ ingredientChecked: ingredient.checked }" v-show="toppingsRemaining > 0 || ingredient.checked">
          <div class="ingredientCheckbox">
            <input type="checkbox" :id="ingredient" :name="ingredient.ingredientName" :value="ingredient" v-model="ingredientsToAdd" v-bind:checked="ingredient.checked" v-on:change="checkedIngredients(ingredient)"/>
            <label :for="ingredient"></label>
          </div>
          <div>
            <p id="ingredientName"><span class="bold">{{ ingredient.ingredientName }}</span></p>
            <p id="ingredientPrice">Price: ${{ Number(ingredient.price).toFixed(2) }}</p>
          </div>
        </div>
      </div>
      <div>
        <h3 id="veggieId">Veggies</h3>
        <div class="veggies ingredientCategory" v-for="ingredient in veggies" v-bind:key="ingredient.ingredientId" v-bind:class="{ ingredientChecked: ingredient.checked }" v-show="toppingsRemaining > 0 || ingredient.checked">
          <div class="ingredientCheckbox">
            <input type="checkbox" :id="ingredient" :name="ingredient.ingredientName" :value="ingredient" v-model="ingredientsToAdd" v-bind:checked="ingredient.checked" v-on:change="checkedIngredients(ingredient)"/>
            <label :for="ingredient"></label>
          </div>
          <div>
            <p id="ingredientName"><span class="bold">{{ ingredient.ingredientName }}</span></p>
            <p id="ingredientPrice">Price: ${{ Number(ingredient.price).toFixed(2) }}</p>
          </div>
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
      customPizza: {},
      ingredientsToAdd: [],
      indexOfCustomPizza: 0,
    };
  },
  computed: {
    computedPrice() {
      let totalPrice = this.basePrice;
      this.ingredientsToAdd.forEach(
        (ingredient) => (totalPrice += ingredient.price)
      );
      return Number(totalPrice).toFixed(2);
    },
    toppingsRemaining() {
      return 9 - this.ingredientsToAdd.length;
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
    getCustomPizzaId(pizza) {
      menuService.createCustomPizza(pizza).then((response) => {
        this.$store.state.cart[this.indexOfCustomPizza].itemId = response.data;
      });
    },
    addToCart() {
      let doughIngredientIndex = this.$store.state.ingredients.findIndex(
        (ingredient) => ingredient.ingredientName === "Dough"
      );
      let doughIngredient = this.$store.state.ingredients[doughIngredientIndex];
      this.customPizza.price = this.computedPrice;
      this.ingredientsToAdd.push(doughIngredient);
      this.ingredientsToAdd.forEach((e) => (e.orderQuantity = 1));
      this.customPizza.ingredients = this.ingredientsToAdd;
      this.getCustomPizzaId(this.customPizza);
      this.customPizza.itemSize = this.pizzaSize;
      this.customPizza.itemType = "Custom";
      this.customPizza.itemCategory = "Pizza";
      this.customPizza.orderQuantity = 1;
      this.customPizza.cartItemId = this.$store.state.currentCartItemId;
      this.$store.commit("ADD_CUSTOM_PIZZA_TO_CART", this.customPizza);
      this.indexOfCustomPizza = this.$store.state.cart.findIndex(
        (e) => e.cartItemId === this.$store.state.currentCartItemId
      );
      this.$store.state.currentCartItemId++;
      this.customPizza = {};
      this.ingredientsToAdd.forEach((e) => this.checkedIngredients(e));
      this.ingredientsToAdd = [];
    },
    checkedIngredients(ingredient) {
      ingredient.checked = !ingredient.checked;
    },
  },
  created() {
    this.getIngredients();
  },
};
</script>

<style scoped>
.ingredientListDiv > h2 {
  background-color: #d20201;
  color: #fff;
  border-radius: 8px;
  text-align: center;
  padding: 16px;
}

.ingredientListDesc {
  background-color: #fff;
  padding: 16px;
  border-radius: 8px;
  margin: 16px 0;
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
  margin-top: 16px;
}

.pizzaDescription > p > .bold {
  font-weight: bold;
}

.ingredientList {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "meatId veggieId sauceId cheeseId"
    "sauce cheese meat veggies";
  gap: 16px;
}

#sauceId {
  grid-area: sauceId;
  color: #d20201;
  text-align: center;
}

#cheeseId {
  grid-area: cheeseId;
  color: #d20201;
  text-align: center;
}

#meatId {
  grid-area: meatId;
  color: #d20201;
  text-align: center;
}

#veggieId {
  grid-area: veggieId;
  color: #d20201;
  text-align: center;
}

.sauces {
  grid-area: sauce;
}

.cheeses {
  grid-area: cheese;
}

.meats {
  grid-area: meat;
}

.veggies {
  grid-area: veggies;
}

.ingredientCategory {
  display: flex;
  align-items: center;
  background-color: #fff;
  margin-top: 16px;
  border-radius: 8px;
  padding: 16px;
}

.ingredientChecked {
  background-color: rgb(225, 225, 225);
}

.ingredientCategory > .ingredientCheckbox {
  padding-right: 16px;
}

#ingredientName {
  margin-bottom: 8px;
}

#ingredientName > .bold {
  font-weight: bold;
}
</style>