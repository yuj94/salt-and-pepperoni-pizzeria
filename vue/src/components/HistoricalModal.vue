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
          <tr v-for="item in this.$store.state.historicalOrders[indexOfOrderId].menuItems" v-bind:key="item.itemId">
            <td class="center">{{ item.itemId }}</td>
            <td v-if="item.itemSize != 0">{{ item.itemSize }}" {{ item.itemName }}</td>
            <td v-else>{{ item.itemName }}</td>
            <td class="center">{{ item.orderQuantity }}</td>
            <td class="center">{{ item.price }}</td>
          </tr>
          <tr v-for="item in this.$store.state.historicalOrders[indexOfOrderId].customPizza" v-bind:key="item.itemId">
            <td class="center">{{ item.pizzaId }}</td>
            <td>{{ item.itemSize }}" Custom Pizza</td>
            <td class="center">{{ item.orderQuantity }}</td>
            <td class="center">{{ item.price }}</td>
          </tr>
        </tbody>
      </table>
      <div class="customerDetails">
        <div class="customerInformation">
          <h2>Customer Information</h2>
          <p>First name: {{ this.$store.state.historicalOrders[indexOfOrderId].firstName }}</p>
          <p>Last name: {{ this.$store.state.historicalOrders[indexOfOrderId].lastName }}</p>
          <p>Email: {{ this.$store.state.historicalOrders[indexOfOrderId].email }}</p>
          <p>Phone: {{ this.$store.state.historicalOrders[indexOfOrderId].phoneNumber }}</p>
        </div>
        <div class="customerAddress" v-if="this.$store.state.historicalOrders[indexOfOrderId].isDelivery">
          <h2>Customer Address</h2>
          <p>Address: {{ this.$store.state.historicalOrders[indexOfOrderId].addressLine }}</p>
          <p>City: {{ this.$store.state.historicalOrders[indexOfOrderId].addressCity }}</p>
          <p>State: {{ this.$store.state.historicalOrders[indexOfOrderId].addressState }}</p>
          <p>Zip code: {{ this.$store.state.historicalOrders[indexOfOrderId].addressZipCode }}</p>
        </div>
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
      return this.$store.state.historicalOrders.findIndex(
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
  padding: 80px;
  z-index: 15;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-inner {
  background-color: #f6f2ed;
  border-radius: 8px;
  padding-bottom: 24px;
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

.toggle-modal-button {
  border: none;
  padding: 16px 24px;
  border-radius: 8px;
  background-color: #d20201;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.5s;
}

.toggle-modal-button:hover {
  background-color: rgba(210, 2, 1, 0.5);
}

.customerDetails {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "information address";
  gap: 16px;
  padding: 16px;
}

.customerInformation {
  grid-area: information;
  min-width: 320px;
}

.customerAddress {
  grid-area: address;
  min-width: 320px;
}

.customerInformation > p,
.customerAddress > p {
  padding-top: 16px;
}

.modal-inner > .toggle-modal-button {
  display: flex;
  margin: 8px auto 0 auto;
}
</style>