<template>
  <div class="ingredientListDiv">
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>

    <div class="loaded" v-else>
      <div class="manageIngredientBanner">
      <h2>Manage Inventory Ingredients</h2>
      <div
            class="ingredient"
            id="doughs"
            v-for="ingredient in dough"
            v-bind:key="ingredient.ingredientId"
          >
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            <p id="ingredientPrice">Price: ${{ ingredient.price }} </p>&nbsp;
            <p id="ingredientOrderQuantity">Quantity: {{ ingredient.orderQuantity }}</p>
          </div>
      <button type="submit">Add Ingredient</button>
      </div>
      <div class="ingredientList">
        <div>
          <h2 id="meatId">Meats</h2>
          <div
            class="ingredient"
            id="meats"
            v-for="ingredient in meats"
            v-bind:key="ingredient.ingredientId"
          >
          <form action="">

          </form>
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            <h3 id="ingredientPrice">$ {{ ingredient.price }} </h3>&nbsp;
            <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
          </div>
        </div>
        <div>
          <h2 id="veggieId">Veggies</h2>
          <div
            class="ingredient"
            id="veggies"
            v-for="ingredient in veggies"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            <h3 id="ingredientPrice">$ {{ ingredient.price }} </h3>&nbsp;
            <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
          </div>
        </div>
        <div>
          <h2 id="sauceId">Sauces</h2>
          <div
            class="ingredient"
            id="sauces"
            v-for="ingredient in sauces"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            <h3 id="ingredientPrice">$ {{ ingredient.price }} </h3>&nbsp;
            <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
          </div>
        </div>
        <div>
          <h2 id="cheeseId">Cheeses</h2>
          <div
            class="ingredient"
            id="cheeses"
            v-for="ingredient in cheeses"
            v-bind:key="ingredient.ingredientId"
          >
            <input
              type="checkbox"
              id="ingredientId"
              name="ingredientName"
              value="ingredient"
            />
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            <h3 id="ingredientPrice">$ {{ ingredient.price }} </h3>&nbsp;
            <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
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
    dough() {
      return this.$store.state.ingredients.filter((ingredient) => {
        return ingredient.ingredientType == "Dough";
        });
    },
  },
  methods: {
    getIngredients() {
      menuService.getPizzaIngredients().then((response) => {
        this.$store.commit("SET_INGREDIENTS", response.data);
        this.isLoading = false;
      });
    },
  },
  created() {
    this.getIngredients();
  },
};
</script>

<style scoped>
.manageIngredientBanner {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
}

.ingredientList {
  font-size: 12px;
  margin: 10px;
  padding: 10px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "meatId veggieId sauceId cheeseId"
    "meat veggies sauce cheese";
}

.ingredient {
  display: flex;
  flex-direction: row;
  align-content: space-around;
}

#meats {
  grid-area: meat;
}

#veggies {
  grid-area: veggies;
}

#sauces {
  grid-area: sauce;
}

#cheeses {
  grid-area: cheese;
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