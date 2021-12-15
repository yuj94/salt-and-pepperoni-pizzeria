<template>
  <div class="pendingOrdersDiv">
    <table id="tblOrders">
      <thead>
        <tr>
          <th></th>
          <th>Order Id</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Address</th>
          <th>State</th>
          <th>City</th>
          <th>Zip Code</th>
          <th>Email Address</th>
          <th>Phone Number</th>
          <th>Order Total</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td>
          <td><input type="text" id="orderIdfilter" v-model="filter.orderId" placeholder="Order Id"/></td>
          <td><input type="text" id="firstNameFilter" v-model="filter.firstName" placeholder="First Name"/></td>
          <td><input type="text" id="lastNameFilter" v-model="filter.lastName" placeholder="Last Name"/></td>
          <td><input type="text" id="addressFilter" v-model="filter.addressLine" placeholder="Address"/></td>
          <td><input type="text" id="stateFilter" v-model="filter.addressState" placeholder="State"/></td>
          <td><input type="text" id="cityFilter" v-model="filter.addressCity" placeholder="City"/></td>
          <td><input type="text" id="zipCodeFilter" v-model="filter.addressZipCode" placeholder="Zip Code"/></td>
          <td><input type="text" id="emailFilter" v-model="filter.email" placeholder="E-Mail"/></td>
          <td><input type="text" id="phoneNumberFilter" v-model="filter.phoneNumber" placeholder="Phone Number"/></td>
          <td><input type="text" id="orderTotalFilter" v-model="filter.orderTotal" placeholder="Order Total"/></td>
          <td> </td>
        </tr>
        <tr v-for="order in filteredList" v-bind:key="order.orderId">
          <button type="button" class="orderDetailsButton" v-on:click="viewOrderDetails(order.orderId)">View Order Details</button>
          <td>{{ order.orderId }}</td>
          <td>{{ order.firstName }}</td>
          <td>{{ order.lastName }}</td>
          <td>{{ order.addressLine }}</td>
          <td>{{ order.addressState }}</td>
          <td>{{ order.addressCity }}</td>
          <td>{{ order.addressZipCode }}</td>
          <td>{{ order.email }}</td>
          <td>{{ order.phoneNumber }}</td>
          <td>{{ order.orderTotal }}</td>
          <button type="button" class="completedButton" v-on:click="markAsCompleted(order.orderId)">Mark As Completed</button>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import orderService from "@/services/OrderService.js";

export default {
  name: "pending-orders-list",
  data() {
    return {
      filter: {
        orderId: "",
        firstName: "",
        lastName: "",
        addressLine: "",
        addressState: "",
        addressCity: "",
        addressZipCode: "",
        email: "",
        phoneNumber: "",
        orderTotal: "",
      },
    };
  },
  computed: {
    filteredList() {
      return this.$store.state.pendingOrders.filter((order) => {
        return order.orderId.toString().includes(this.filter.orderId) &&
          order.firstName.toLowerCase().includes(this.filter.firstName.toLowerCase()) &&
          order.lastName.toLowerCase().includes(this.filter.lastName.toLowerCase()) &&
          order.addressLine.toLowerCase().includes(this.filter.addressLine.toLowerCase()) &&
          order.addressState.toLowerCase().includes(this.filter.addressState.toLowerCase()) &&
          order.addressCity.toLowerCase().includes(this.filter.addressCity.toLowerCase()) &&
          order.email.toLowerCase().includes(this.filter.email.toLowerCase()) &&
          order.addressZipCode.toString().includes(this.filter.addressZipCode) &&
          order.phoneNumber.toLowerCase().includes(this.filter.phoneNumber.toLowerCase()) &&
          order.orderTotal.toString().includes(this.filter.orderTotal)
      });
    },
  },
  methods: {
    getPendingOrders() {
      orderService.getAllPendingOrders().then((response) => {
        this.$store.commit("SET_PENDING_ORDERS", response.data);
      });
    },
    markAsCompleted(orderId){
      orderService.setOrderToComplete(orderId).then((response)=>{
        this.$store.commit("SET_ORDER_TO_COMPLETE", response.data)
      })
    },
    viewOrderDetails(orderId){ 
      orderService.getMenuItemDetails(orderId).then((response)=> {
        this.$store.commit("SET_MENU_ITEM_DETAILS_PENDING", response.data)
      })
      orderService.getCustomPizzaDetails(orderId).then((response)=>{
        this.$store.commit("SET_CUSTOM_PIZZA_DETAILS_PENDING", response.data)
      })
    },
},
    created() {
      this.getPendingOrders();
      this.markAsCompleted();
      this.viewOrderDetails();
    },
};
</script>

<style scoped>
.pendingOrdersDiv {
  padding-top: 192px;
}
</style>