<template>
  <div class="menuInventoryDiv">
    <div class="manageItemBanner">
      <h2>Manage Menu Items</h2>

      <div>
        <button id ="addMenuItemButton" v-if="showForm === false" v-on:click.prevent="showForm = true">Add Menu Item</button>
          <form v-on:submit.prevent="addNewMenuItem" v-if="showForm === true">
            <div class="form-element">
              <label for="name">Name: </label>
              <input id="name" type="text" v-model="newMenuItem.itemName" />
            </div>

            <div class="form-element">
              <label for="description">Description: </label>
              <input id="description" type="text" v-model="newMenuItem.itemDescription" />
            </div>  

            <div class="form-element">
              <label for="category">Type: </label>
              <select name="category" id="category" v-model="newMenuItem.itemCategory">
                <option value="appetizer">Appetizer</option>
                <option value="salad">Salad</option>
                <option value="dessert">Dessert</option>
                <option value="drink">Drink</option>
              </select>
            </div>
            
            <div class="form-element">
              <label for="price">Price: </label>
              <input id="price" type="number" min="0" max="25.00" v-model="newMenuItem.price" />
            </div>

            <div class="form-element">
              <label for="quantity">Quantity: </label>
              <input id="quantity" type="number" min="0" v-model.number="newMenuItem.totalQuantity">
            </div>

            <input type="submit" value="Submit" />
            <input type="button" value="Cancel" v-on:click.prevent="resetForm" /> 
          </form>
      </div>
    </div>

    <div class="menuItemList">
      <div>
        <h2 id="appetizerId">Appetizers</h2>
        <div
          class="menuItem"
          id="appetizers"
          v-for="item in appetizers"
          v-bind:key="item.itemId">
          <div>
            <label for="itemId"> {{ item.itemName }} </label>
          </div>
          <div>
            <h3 id="itemPrice">Current Price: ${{ item.price }} </h3>
            <label for="price">Update Price: ${{ item.price }}</label>
            <input id="price" name="price" type="number" min="0" max="9.99"/>
          </div>
          <div>
            <h3 id="itemQuantity">Current quantity: {{ item.totalQuantity }}</h3>
            <label for="quantity">Update Quantity: {{ item.totalQuantity }} </label>
            <input id="quantity" name="quantity" type="number" min="0"/>
          </div>
        </div>
      </div>

      <div>
        <h2 id="saladId">Salads</h2>
        <div
          class="menuItem"
          id="salads"
          v-for="item in salads"
          v-bind:key="item.itemId">
          <div>
            <label for="itemId"> {{ item.itemName }} </label>
          </div>
          <div>
            <h3 id="itemPrice">Current Price: ${{ item.price }} </h3>
            <label for="price">Update Price: ${{ item.price }}</label>
            <input id="price" name="price" type="number" min="0" max="9.99"/>
          </div>
          <div>
            <h3 id="itemQuantity">Current quantity: {{ item.totalQuantity }}</h3>
            <label for="quantity">Update Quantity: {{ item.totalQuantity }} </label>
            <input id="quantity" name="quantity" type="number" min="0"/>
          </div>
        </div>
      </div>

      <div>
        <h2 id="dessertId">Desserts</h2>
        <div
          class="menuItem"
          id="dessert"
          v-for="item in desserts"
          v-bind:key="item.itemId">
          <div>
            <label for="itemId"> {{ item.itemName }} </label>
          </div>
          <div>
            <h3 id="itemPrice">Current Price: ${{ item.price }} </h3>
            <label for="price">Update Price: ${{ item.price }}</label>
            <input id="price" name="price" type="number" min="0" max="9.99"/>
          </div>
          <div>
            <h3 id="itemQuantity">Current quantity: {{ item.totalQuantity }}</h3>
            <label for="quantity">Update Quantity: {{ item.totalQuantity }} </label>
            <input id="quantity" name="quantity" type="number" min="0"/>
          </div>
        </div>
      </div>
        
      <div>
        <h2 id="drinkId">Drinks</h2>
        <div
          class="menuItem"
          id="drinks"
          v-for="item in drinks"
          v-bind:key="item.itemId">
          <div>
            <label for="itemId"> {{ item.itemName }} </label>
          </div>
          <div>
            <h3 id="itemPrice">Current Price: ${{ item.price }} </h3>
            <label for="price">Update Price: ${{ item.price }}</label>
            <input id="price" name="price" type="number" min="0" max="9.99"/>
          </div>
          <div>
            <h3 id="itemQuantity">Current quantity: {{ item.totalQuantity }}</h3>
            <label for="quantity">Update Quantity: {{ item.totalQuantity }} </label>
            <input id="quantity" name="quantity" type="number" min="0"/>
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
      newMenuItem: {}
    };
  },
  computed: {
    pizzas() {
      return this.$store.state.menuItems.filter((item) => {
        return item.itemCategory == "Pizza";
      });
    },
    appetizers() {
      return this.$store.state.menuItems.filter((item) => {
        return item.itemCategory == "Appetizer";
      });
    },
    salads() {
      return this.$store.state.menuItems.filter((item) => {
        return item.itemCategory == "Salad";
      });
    },
    desserts() {
      return this.$store.state.menuItems.filter((item) => {
        return item.itemCategory == "Dessert";
      });
    },
    drinks() {
      return this.$store.state.menuItems.filter((item) => {
        return item.itemCategory == "Drink";
      });
    },
    
  },
  methods: {
    getMenuItems() {
      menuService.getAllMenuItems().then((response) => {
        this.$store.commit("SET_MENU_ITEMS", response.data);
        this.isLoading = false;
      });
    },
    addNewMenuItem() {
      menuService.addNewMenuItem(this.newMenuItem).then(() => {
      this.$store.commit("ADD_MENU_ITEM",this.newMenuItem);
      this.resetForm();
    });
    },
    resetForm() {
      this.showForm = false;
    },
  },
  created() {
    this.getMenuItems();
  },
};
</script>

<style scoped>
.manageItemBanner {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
}

.menuItemList {
    text-align: center;
    font-size: 12px;
    margin: 0;
    padding: 0;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas:
        "pizzaId appetizerId saladId dessertId drinkId"
        "pizzas appetizers salads desserts drinks";
}

.menuItem {
    display: flex;
    flex-direction: column;
    align-content: space-around;
}

#pizzas {
    grid-area: pizzas;
}

#appetizers {
    grid-area: appetizers;
}

#salads {
    grid-area: salads;

}

#desserts {
    grid-area: desserts;
}

#drinks {
    grid-area: drinks;

}

#pizzaId {
  grid-area: pizzaId;
}

#appetizerId {
  grid-area: pizzaId;
}

#saladId {
  grid-area: appetizerId;
}

#dessertId {
  grid-area: dessertId;
}

#drinkId {
  grid-area: drinkId;
}

</style>