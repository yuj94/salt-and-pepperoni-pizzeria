<template>
  <div class="ingredientListDiv">
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>

    <div class="loaded" v-else>
      <div class="customHeaderDiv">
        <h2>Create your own custom pizza.</h2>
        <button>Add to Cart</button>
        <label for="pizzaSize">Choose a pizza size:</label>
        <select name="pizzaSize" id="pizzaSize" v-model="pizzaSize">
          <option value="12">12"</option>
          <option value="16">16"</option>
          <option value="18">18"</option>
        </select>
      </div>
      <p>
        For our {{ this.pizzaSize }}" custom pizzas, our prices start at ${{
          this.basePrice
        }}
        for the dough plus the cost of the ingredients selected.
      </p>
      <p>You can select up to {{this.toppingsRemaining}} more ingredients for your custom pizza.</p>

      <div class="ingredientList">
        <div>
          <h2 id="meatId">Meats</h2>
          <div
            class="meats"
            v-for="ingredient in meats"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
              v-model="customPizza.ingredients"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
            <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
            &nbsp;
            <select name="ingredientQuantity" id="ingredientQuantity">
              <option value="1">Regular</option>
              <option value="2">Double</option>
            </select>
          </div>
        </div>
        <div>
          <h2 id="veggieId">Veggies</h2>
          <div
            class="veggies"
            v-for="ingredient in veggies"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
            <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
            &nbsp;
            <p id="ingredientOrderQuantity">
              Order quantity: {{ ingredient.orderQuantity }}
            </p>
          </div>
        </div>
        <div>
          <h2 id="sauceId">Sauces</h2>
          <div
            class="sauces"
            v-for="ingredient in sauces"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
            <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
            &nbsp;
            <p id="ingredientOrderQuantity">
              Order quantity: {{ ingredient.orderQuantity }}
            </p>
          </div>
        </div>
        <div>
          <h2 id="cheeseId">Cheeses</h2>
          <div
            class="cheeses"
            v-for="ingredient in cheeses"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
            <p id="ingredientPrice">Price: ${{ ingredient.price }}</p>
            &nbsp;
            <p id="ingredientOrderQuantity">
              Order quantity: {{ ingredient.orderQuantity }}
            </p>
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
      isLoading: true,
      customPizza: {
        ingredients: {},
      },
      toppingsRemaining: 9,
    };
  },
  computed: {
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
    pizzaSize() {
      return 16;
    },
  },
  methods: {
    getIngredients() {
      menuService.getPizzaIngredients().then((response) => {
        this.$store.commit("SET_INGREDIENTS", response.data);
        this.isLoading = false;
      });
    },
    adjustToppingsRemaining() {

    },
  },
  created() {
    this.getIngredients();
  },
};
</script>

<style scoped>
.ingredientList {
  font-size: 12px;
  margin: 0;
  padding: 0;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "meatId veggieId sauceId cheeseId"
    "meat veggies sauce cheese";
}

.customHeaderDiv {
  display: flex;
  flex-direction: column;
}

.customHeaderDiv > #pizzaSize {
  width: 50px;
}

.meats {
  grid-area: meat;
  display: flex;
  flex-direction: column;
  align-content: space-around;
}

.veggies {
  grid-area: veggies;
  display: flex;
  flex-direction: column;
  align-content: space-around;
}

.sauces {
  grid-area: sauce;
  display: flex;
  flex-direction: column;
  align-content: space-around;
}

.cheeses {
  grid-area: cheese;
  display: flex;
  flex-direction: column;
  align-content: space-around;
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
</style>