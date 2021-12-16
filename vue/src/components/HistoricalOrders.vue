<template>
  <div>
    <historical-modal v-show="showModal" v-bind:orderId="orderId" @toggle='toggleModal(orderId)'></historical-modal>
  <div class="historicalOrdersDiv">
    <table class="styled-table">
      <thead>
        <tr>
          <th></th>
          <th>Order Id</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email Address</th>
          <th>Phone Number</th>
          <th>Order Total</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td></td>
          <td><input type="text" id="orderIdFilter" v-model="filter.orderId" placeholder="Order Id"/></td>
          <td><input type="text" id="firstNameFilter" v-model="filter.firstName" placeholder="First Name"/></td>
          <td><input type="text" id="lastNameFilter" v-model="filter.lastName" placeholder="Last Name"/></td>
          <td><input type="text" id="emailFilter" v-model="filter.email" placeholder="E-Mail"/></td>
          <td><input type="text" id="phoneNumberFilter" v-model="filter.phoneNumber" placeholder="Phone Number"/></td>
          <td><input type="text" id="orderTotalFilter" v-model="filter.orderTotal" placeholder="Order Total"/></td>
          <td></td>
        </tr>
        <tr v-for="order in this.filteredList" v-bind:key="order.orderId" v-bind:filter="filter">
          <button class="toggle-modal-button" v-on:click='toggleModal(order.orderId)'>View Order Details</button>
          <td>{{ order.orderId }}</td>
          <td>{{ order.firstName }}</td>
          <td>{{ order.lastName }}</td>
          <td>{{ order.email }}</td>
          <td>{{ order.phoneNumber }}</td>
          <td>{{ order.orderTotal }}</td>
          <button type="button" class="notCompletedButton" v-on:click="markAsNotCompleted(order.orderId)">Mark As Not Completed</button>
        </tr>
      </tbody>
    </table>
    <div>
      <h2>Customer Name: </h2>
      <p>{{this.$store.state.historicalOrders[indexOfOrderId].firstName}}</p>
      <p>{{this.$store.state.historicalOrders[indexOfOrderId].lastName}}</p>
      <h2>Customer Address: </h2>
      <p>{{this.$store.state.historicalOrders[indexOfOrderId].addressLine}}</p>
      <p>{{this.$store.state.historicalOrders[indexOfOrderId].addressState}}</p>
      <p>{{this.$store.state.historicalOrders[indexOfOrderId].addressCity}}</p>
      <p>{{this.$store.state.historicalOrders[indexOfOrderId].addressZipCode}}</p>
    </div>
    </div>
  </div>
</template>

<script>
import orderService from '@/services/OrderService.js';
import HistoricalModal from '@/components/HistoricalModal.vue';

export default {
  name: "historical-orders-list",
  components: {
      HistoricalModal
  },
  data() {
    return {
      filter: {
        orderId: "",
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        orderTotal: "",
      },
      historicalOrders: [],
      showModal: false
    };
  },
  computed: {
    filteredList() {
      return this.$store.state.historicalOrders.filter((order) => {
        return  order.orderId.toString().includes(this.filter.orderId.toString()) &&
          order.firstName.toLowerCase().includes(this.filter.firstName.toLowerCase()) &&
          order.lastName.toLowerCase().includes(this.filter.lastName.toLowerCase()) &&
          order.email.toLowerCase().includes(this.filter.email.toLowerCase()) &&
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
    markAsNotCompleted(orderId){
      orderService.setOrderToNotComplete(orderId).then((response)=>{
        this.$store.commit("SET_ORDER_TO_NOT_COMPLETE", response.data);
        window.location.reload();
      })
    },
    toggleModal(orderId){
      this.orderId = orderId;
      this.showModal = !this.showModal;
    }
  },
  created() {
    this.getHistoricalOrders();
     
  },
};
</script>

<style scoped>
.toggle-modal-button{
  width: 100px;
  height: 50px;
}

#orderIdFilter{
  width: 60px;
}

#stateFilter{
  width: 60px;
}

#phoneNumberFilter{
  width: 105px;
}

#orderTotalFilter{
  width: 75px;
}

.historicalOrdersDiv {
  padding-top: 192px;
}

.styled-table{
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
}

</style>