<template>
  <div class="ingredientInventoryDiv">
    <div class="manageIngredientBanner">
      <h2>Manage Inventory Ingredients</h2>

      <div
            class="ingredient"
            id="doughs"
            v-for="ingredient in dough"
            v-bind:key="ingredient.ingredientId">
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            <p id="ingredientPrice">Price: ${{ ingredient.price }} </p>&nbsp;
            <p id="ingredientOrderQuantity">Quantity: {{ ingredient.orderQuantity }}</p>
      </div>

      <div>
      <button id ="addIngredientButton" v-if="showForm === false" v-on:click.prevent="showForm = true">Add Ingredient</button>
        <form v-on:submit.prevent="addIngredient" v-if="showForm === true">
          <div class="form-element">
            <label for="ingredientName">Name: </label>
            <input id="ingredientName" type="text" v-model="newIngredient.ingredientName" />
          </div>

          <div class="form-element">
            <label for="type">Type: </label>
            <select name="type" id="type" v-model="newIngredient.ingredientType">
              <option value="Meat">Meat</option>
              <option value="Veggies">Veggie</option>
              <option value="Sauce">Sauce</option>
              <option value="Cheese">Cheese</option>
            </select>
          </div>  

          <div class="form-element">
            <label for="price">Price: </label>
            <input id="price" type="number" min="0" v-model="newIngredient.price" />
          </div>

          <div class="form-element">
            <label for="totalQuantity">Quantity: </label>
            <input id="totalQuantity" type="number" min="0" v-model.number="newIngredient.totalQuantity">
          </div>

          <input type="submit" value="Submit" />
          <input type="button" value="Cancel" v-on:click.prevent="resetForm" /> 
        </form>
      </div>
    </div>

      <div class="ingredientList">
        <div>
          <h2 id="meatId">Meats</h2>
          <div
            class="ingredient"
            id="meats"
            v-for="ingredient in meats"
            v-bind:key="ingredient.ingredientId">
            <div>
              <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            </div>

            <div>
              <h3 id="ingredientPrice">Current Price: ${{ ingredient.price }} </h3>&nbsp;
              <label for="price">Update Price: ${{ ingredient.price }}</label>
              <form v-on:submit.prevent="updateIngredientPrice(ingredient)">
                <input id="price" name="price" type="number" min="0" max="9.99" v-model="getCurrentIngredient(ingredient).price"/>
                <input type="submit" value="Update"/>
              </form>
            </div>

            <div>
              <h3 id="ingredientOrderQuantity">Current Quantity: {{ ingredient.totalQuantity }}</h3>
              <label for="totalQuantity">Update Quantity: {{ ingredient.totalQuantity }} </label>
              <input id="quantity" name="totalQuantity" type="number" min="0" v-model="getCurrentIngredient(ingredient).totalQuantity"/>
              <button id ="totalQuantity" v-on:click="updateIngredientQuantity(ingredient)">Update</button>
            </div>
          </div>
        </div>

        <div>
          <h2 id="veggieId">Veggies</h2>
          <div
            class="ingredient"
            id="veggies"
            v-for="ingredient in veggies"
            v-bind:key="ingredient.ingredientId">
            <div>
              <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            </div>
            <div>
              <h3 id="ingredientPrice">Current Price: ${{ ingredient.price }} </h3>&nbsp;
              <label for="price">Update Price: ${{ ingredient.price }}</label>
              <input id="price" name="price" type="number" min="0" max="9.99"/>
              <button id ="updatePriceButton">Update</button>
            </div>
          <div>
            <h3 id="ingredientOrderQuantity">Current Quantity: {{ ingredient.totalQuantity }}</h3>
            <label for="quantity">Update Quantity: {{ ingredient.totalQuantity }} </label>
            <input id="quantity" name="quantity" type="number" min="0"/>
            <button id ="updateQuantityButton">Update</button>
          </div>
          </div>
        </div>

        <div>
          <h2 id="sauceId">Sauces</h2>
          <div
            class="ingredient"
            id="sauces"
            v-for="ingredient in sauces"
            v-bind:key="ingredient.ingredientId">
            <div>
              <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
            </div>
            <div>
              <h3 id="ingredientPrice">Current Price: ${{ ingredient.price }} </h3>&nbsp;
              <label for="price">Update Price: ${{ ingredient.price }}</label>
              <input id="price" name="price" type="number" min="0" max="9.99"/>
              <button id ="updatePriceButton">Update</button>
            </div>
            <div>
              <h3 id="ingredientOrderQuantity">Current Quantity: {{ ingredient.totalQuantity }}</h3>
              <label for="quantity">Update Quantity: {{ ingredient.totalQuantity }} </label>
              <input id="quantity" name="quantity" type="number" min="0"/>
              <button id ="updateQuantityButton">Update</button>
            </div>
          </div>
        </div>

        <div>
          <h2 id="cheeseId">Cheeses</h2>
          <div
            class="ingredient"
            id="cheeses"
            v-for="ingredient in cheeses"
            v-bind:key="ingredient.ingredientId">
            <div>
            <label for="ingredientId"> {{ ingredient.ingredientName }} </label>
          </div>
          <div>
            <h3 id="ingredientPrice">Current Price: ${{ ingredient.price }} </h3>&nbsp;
            <label for="price">Update Price: ${{ ingredient.price }}</label>
            <input id="price" name="price" type="number" min="0" max="9.99"/>
            <button id ="updatePriceButton">Update</button>
          </div>
          <div>
            <h3 id="ingredientOrderQuantity">Current Quantity: {{ ingredient.totalQuantity }}</h3>
            <label for="quantity">Update Quantity: {{ ingredient.totalQuantity }} </label>
            <input id="quantity" name="quantity" type="number" min="0"/>
            <button id ="updateQuantityButton">Update</button>
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
      showForm: false,
      newIngredient: {},
      currentIngredient: {},
      updatedIngredient: []
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
    addIngredient() {
      menuService.addIngredient(this.newIngredient).then(() => {
      this.$store.commit(this.newIngredient);
      this.resetForm();
      });
    },
    updateIngredientPrice(ingredient) {
      this.updatedIngredient.ingredientId = ingredient.ingredientId;
      menuService.updateIngredientPrice(this.updatedIngredient.ingredientId, this.updatedIngredient.price).then(() => {
      this.getIngredients();
      });
    },
    updateIngredientQuantity(ingredient) {
      this.updatedIngredient.ingredientId = ingredient.ingredientId;
      menuService.updateIngredientQuantity(this.updatedIngredient.ingredientId, this.updatedIngredient.totalQuantity).then(() => {
      this.getIngredients();
      });
    },
    getCurrentIngredient(ingredient) {
      return ingredient;
    },
    resetForm() {
      this.showForm = false;
    },
  },
  created() {
    this.getIngredients();
  },
};
</script>

<style scoped>
.ingredientInventoryDiv{
  padding-top: 192px;
}









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