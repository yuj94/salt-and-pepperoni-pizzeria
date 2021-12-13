<template>
  <div>
    <table id="tblOrders">
      <thead>
        <tr>
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
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><input type="text" id="orderIdfilter" v-model="filter.orderId"/></td>
          <td><input type="text" id="firstNameFilter" v-model="filter.firstName"/></td>
          <td><input type="text" id="lastNameFilter" v-model="filter.lastName"/></td>
          <td><input type="text" id="addressFilter" v-model="filter.addressLine"/></td>
          <td><input type="text" id="stateFilter" v-model="filter.addressState"/></td>
          <td><input type="text" id="cityFilter" v-model="filter.addressCity"/></td>
          <td><input type="text" id="zipCodeFilter" v-model="filter.addressZipCode"/></td>
          <td><input type="text" id="EmailFilter" v-model="filter.email"/></td>
          <td><input type="text" id="phoneNumberFilter" v-model="filter.phoneNumber"/></td>
          <td><input type="text" id="orderTotalFilter" v-model="filter.orderTotal"/></td>
        </tr>
        <tr v-for="order in filteredList" v-bind:key="order.orderId">
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
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import orderService from '@/services/OrderService.js';

export default {
  name: "historical-orders-list",
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
      return this.$store.state.historicalOrders.filter((order) => {
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
    getHistoricalOrders() {
      orderService.getAllHistoricalOrders().then((response) => {
        this.$store.commit("SET_HISTORICAL_ORDERS", response.data);
      });
    },
  },
  created() {
    this.getHistoricalOrders();
  },
};
</script>

<style>

</style>