<template>
  <div class="menuInventoryDiv">
       <div class="manageItemBanner">
        <h2>Manage Menu Items</h2>
      <button type="submit">Add Menu Item</button>
      </div>
      <div class="menuItemList">
        <div>
          <h2 id="pizzaId">Pizzas</h2>
          <div
            class="menuItem"
            id="pizzas"
            v-for="item in pizzas"
            v-bind:key="item.itemId"
          >
          <div>
            <input
              type="text"
              id="itemId"
              name="itemName"
              value="Change Item Name"
            />
            <label for="itemId"> {{ item.itemName }} </label>
          </div>
          <div>

              <h3 id="itemPrice">Current Price: ${{ item.price }} </h3>
             <label for="quantity">Update Price: ${{ item.quantity }}</label>
                <input id="quantity" name="quantity" type="number" min="0" max="9.99"/>
            
            </div>
            <div>
                <h3 id="itemQuantity">Current quantity: {{item.totalQuantity}}</h3>
                 <label for="quantity">Update Quantity: {{ item.totalQuantity }} </label>
                <input id="quantity" name="quantity" type="number"/>
            </div>
          </div>
        </div>
        <div>
          <h2 id="appetizerId">Appetizers</h2>
          <div
            class="menuItem"
            id="appetizers"
            v-for="item in appetizers"
            v-bind:key="item.itemId"
          >
            <label for="itemId"> {{ item.itemName }} </label>
            <h3 id="itemPrice">${{ item.price }} </h3>
            <h3 id="itemQuantity">{{ item.totalQuantity }}</h3>
          </div>
        </div>
        <div>
          <h2 id="saladId">Salads</h2>
          <div
            class="menuItem"
            id="salads"
            v-for="item in salads"
            v-bind:key="item.itemId"
          >
            <label for="itemId"> {{ item.itemName }} </label>
            <h3 id="itemPrice">${{ item.price }} </h3>
            <h3 id="itemQuantity">{{ item.totalQuantity }}</h3>
          </div>
        </div>

        <div>
          <h2 id="dessertId">Desserts</h2>
          <div
            class="menuItem"
            id="dessert"
            v-for="item in desserts"
            v-bind:key="item.itemId"
          >
            <label for="itemId"> {{ item.itemName }} </label>
            <h3 id="itemPrice">${{ item.price }} </h3>
            <h3 id="itemQuantity">{{ item.totalQuantity }}</h3>
          </div>
        </div>
        
        <div>
          <h2 id="drinkId">Drinks</h2>
          <div
            class="menuItem"
            id="drinks"
            v-for="item in drinks"
            v-bind:key="item.itemId"
          >
            <label for="itemId"> {{ item.itemName }} </label>
            <h3 id="itemPrice">${{ item.price }} </h3>
            <h3 id="itemQuantity">{{ item.totalQuantity }}</h3>
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