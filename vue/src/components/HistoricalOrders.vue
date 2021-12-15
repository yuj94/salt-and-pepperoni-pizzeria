<template>
  <div>
    <global-modal v-show="showModal" v-bind:orderId="orderId" @toggle='toggleModal(orderId)'></global-modal>
    <table id="tblOrders">
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
  </div>
</template>

<script>
import orderService from '@/services/OrderService.js';
import GlobalModal from '@/components/GlobalModal.vue';

export default {
  name: "historical-orders-list",
  components: {
      GlobalModal
  },
  data() {
    return {
      orderId: Object,
      filter: {
        orderId: "",
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        orderTotal: "",
      },
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
          // order.orderId === this.filter.orderId
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

<style>
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
</style>