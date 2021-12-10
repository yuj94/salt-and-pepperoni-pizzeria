<template>
  <div class="ingredientListDiv">
    <div class="loading" v-if="isLoading">
      <img src="../assets/loader.gif" />
    </div>

    <div class="loaded" v-else>
      <div class="ingredientList">
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
          />
          <label for="ingredientId"> {{ ingredient.ingredientName }}</label>
          <h3 id="ingredientPrice">${{ ingredient.price }}</h3>
          <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
        </div>
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
          <h3 id="ingredientPrice">${{ ingredient.price }}</h3>
          <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
        </div>
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
          <h3 id="ingredientPrice">${{ ingredient.price }}</h3>
          <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
        </div>
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
          <h3 id="ingredientPrice">${{ ingredient.price }}</h3>
          <h3 id="ingredientOrderQuantity">{{ ingredient.orderQuantity }}</h3>
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