<template>
  <div class="global-modal">
    <div class="modal-inner">
      <table class="styled-table">
        <thead>
          <tr>
            <th>Item Id</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in this.$store.state.pendingOrders[indexOfOrderId].menuItems" v-bind:key="item.itemId">
            <td class="center">{{ item.itemId }}</td>
            <td v-if="item.itemSize != 0">{{ item.itemSize }}" {{ item.itemName }}</td>
            <td v-else>{{ item.itemName }}</td>
            <td class="center">{{ item.orderQuantity }}</td>
            <td class="center" v-if="item.price != null">${{ Number(item.price).toFixed(2) }} each</td>
          </tr>
          <tr v-for="item in this.$store.state.pendingOrders[indexOfOrderId].customPizza" v-bind:key="item.itemId">
            <td class="center" v-if="item.price != null">{{ item.pizzaId }}</td>
            <td v-if="item.price != null">{{ item.itemSize }}" Custom Pizza</td>
            <td class="center" v-if="item.price != null">{{ item.orderQuantity }}</td>
            <td class="center" v-if="item.price != null">${{ Number(item.price).toFixed(2) }} each</td>
          </tr>
        </tbody>
      </table>
      <div>
        <h2>Customer Name:</h2>
        <p>{{ this.$store.state.pendingOrders[indexOfOrderId].firstName }}</p>
        <p>{{ this.$store.state.pendingOrders[indexOfOrderId].lastName }}</p>
        <h2>Customer Address:</h2>
        <p>{{ this.$store.state.pendingOrders[indexOfOrderId].addressLine }}</p>
        <p>{{ this.$store.state.pendingOrders[indexOfOrderId].addressState }}</p>
        <p>{{ this.$store.state.pendingOrders[indexOfOrderId].addressCity }}</p>
        <p>{{ this.$store.state.pendingOrders[indexOfOrderId].addressZipCode }}</p>
      </div>
      <button class="toggle-modal-button" v-on:click="toggleModal()">Return</button>
    </div>
  </div>
</template>

<script>
import orderService from "@/services/OrderService.js";

export default {
  data() {
    return {};
  },
  computed: {
    indexOfOrderId() {
      return this.$store.state.pendingOrders.findIndex(
        (e) => e.orderId === this.orderId
      );
    },
  },
  name: "global-modal",
  props: {
    orderId: Number,
  },
  // props: ["orderId"],
  methods: {
    toggleModal() {
      this.$emit("toggle");
    },
    viewOrderDetails(orderId) {
      orderService.getMenuItemDetails(orderId).then((response) => {
        this.$store.commit("SET_MENU_ITEM_DETAILS", response.data);
      });
      orderService.getCustomPizzaDetails(orderId).then((response) => {
        this.$store.commit("SET_CUSTOM_PIZZA_DETAILS", response.data);
      });
    },
  },
  created() {
    this.viewOrderDetails(this.orderId);
  },
};
</script>

<style scoped>
.global-modal {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgb(0, 0, 0, 0.75);
  padding: 192px;
  z-index: 15;
}

.modal-inner {
  width: 100%;
  height: 100%;
  background-color: #f6f2ed;
  border-radius: 8px;
}

.styled-table {
  border-collapse: collapse;
  width: 100%;
  border-radius: 8px;
  overflow: hidden;
}

.styled-table > thead > tr {
  background-color: #d20201;
  color: #ffffff;
}

.styled-table > thead > tr > th,
.styled-table > tbody > tr > td {
  padding: 16px;
}

.styled-table > tbody > tr:nth-of-type(odd) {
  background-color: #fff;
}

.styled-table > tbody > tr:nth-of-type(even) {
  background-color: rgb(225, 225, 225);
}

.center {
  text-align: center;
}

.toggle-modal-button,
.completedButton {
  border: none;
  padding: 8px 24px;
  border-radius: 8px;
  background-color: #d20201;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.5s;
}

.toggle-modal-button:hover,
.completedButton:hover {
  background-color: rgba(210, 2, 1, 0.5);
}














/* #tblOrders {
  width: 100%;
}

.toggle-modal-button {
  width: 200px;
  height: 200px;
  background-color: white;
} */





/* .styled-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  width: 100%;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
  background-color: #d20201;
  color: #ffffff;
  text-align: left;
}

.styled-table th,
.styled-table td {
  padding: 12px 15px;
}

.styled-table tbody tr {
  border-bottom: thin solid #f6f2ed;
}

.styled-table tbody tr:nth-of-type(even) {
  background-color: #fff;
}

.styled-table tbody tr:last-of-type {
  border-bottom: thin solid #d20201;
}

.styled-table tbody tr.active-row {
  font-weight: bold;
  color: #d20201;
} */
</style>