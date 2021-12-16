<template>
  <div>
    <pending-modal v-show="showModal" v-bind:orderId="orderId" @toggle="toggleModal(orderId)"></pending-modal>
    <div class="pendingOrdersDiv">
      <table class="styled-table">
        <thead>
          <tr>
            <th></th>
            <th>Order Id</th>
            <th>Time Since Placed</th>
            <th>Delivery</th>
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
            <td>
              <input type="text" id="orderIdFilter" v-model="filter.orderId" placeholder="Order Id" />
            </td>
            <td class="center">Hrs:Mins:Secs</td>
            <td class="center">
              <input type="checkbox" id="deliveryFilter" v-model="filter.isDelivery" v-on:click="isDeliveryFilter()"/>
            </td>
            <td>
              <input type="text" id="firstNameFilter" v-model="filter.firstName" placeholder="First name" />
            </td>
            <td>
              <input type="text" id="lastNameFilter" v-model="filter.lastName" placeholder="Last name" />
            </td>
            <td>
              <input type="text" id="emailFilter" v-model="filter.email" placeholder="Email" />
            </td>
            <td>
              <input type="text" id="phoneNumberFilter" v-model="filter.phoneNumber" placeholder="Phone" />
            </td>
            <td>
              <input type="text" id="orderTotalFilter" v-model="filter.orderTotal" placeholder="Order Total" />
            </td>
            <td></td>
          </tr>
          <tr v-for="order in this.filteredList" v-bind:key="order.orderId" v-bind:filter="filter">
            <button class="toggle-modal-button" v-on:click="toggleModal(order.orderId)">Order Details</button>
            <td class="center">{{ order.orderId }}</td>
            <td class="center">{{order.timeElapsed.substring(1, 8)}}</td>
            <td class="center">{{(order.isDelivery) ? 'Delivery' : 'Takeout'}}</td>
            <td>{{ order.firstName }}</td>
            <td>{{ order.lastName }}</td>
            <td>{{ order.email }}</td>
            <td class="center">{{ order.phoneNumber }}</td>
            <td class="center">${{ Number(order.orderTotal).toFixed(2) }}</td>
            <button type="button" class="completedButton" v-on:click="markAsCompleted(order.orderId)">Mark Completed</button>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import orderService from "@/services/OrderService.js";
import PendingModal from "@/components/PendingModal.vue";

export default {
  name: "pending-orders-list",
  components: {
    PendingModal,
  },
  data() {
    return {
      filter: {
        orderId: "",
        isDelivery: false,
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        orderTotal: "",
      },
      pendingOrders: [],
      showModal: false,
    };
  },
  computed: {
    filteredList() {
      return this.$store.state.pendingOrders.filter((order) => {
        return (
          order.orderId.toString().includes(this.filter.orderId.toString()) &&
          ((this.filter.isDelivery && order.isDelivery) || (!this.filter.isDelivery && !order.isDelivery || order.isDelivery)) &&
          order.firstName
            .toLowerCase()
            .includes(this.filter.firstName.toLowerCase()) &&
          order.lastName
            .toLowerCase()
            .includes(this.filter.lastName.toLowerCase()) &&
          order.email.toLowerCase().includes(this.filter.email.toLowerCase()) &&
          order.phoneNumber
            .toLowerCase()
            .includes(this.filter.phoneNumber.toLowerCase()) &&
          order.orderTotal.toString().includes(this.filter.orderTotal)
        );
      });
    },
  },
  methods: {
    getPendingOrders() {
      orderService.getAllPendingOrders().then((response) => {
        this.$store.commit("SET_PENDING_ORDERS", response.data);
      });
    },

    markAsCompleted(orderId) {
      orderService.setOrderToComplete(orderId).then((response) => {
        this.$store.commit("SET_ORDER_TO_COMPLETE", response.data);
        window.location.reload();
      });
    },
    toggleModal(orderId) {
      this.orderId = orderId;
      this.showModal = !this.showModal;
    },
  },
  created() {
    this.getPendingOrders();
  },
};
</script>

<style scoped>
.pendingOrdersDiv {
  /* padding: 192px 24px 0 24px; */
  padding: 0 24px;
}

.styled-table {
  border-collapse: collapse;
  border-radius: 8px;
  overflow: hidden;
  width: 100%;
  table-layout: fixed;
}

.styled-table > thead > tr {
  background-color: #d20201;
  color: #ffffff;
}

.styled-table > thead > tr > th,
.styled-table > tbody > tr > td {
  padding: 16px;
}

.styled-table > tbody > tr > button {
  margin: 16px;
}

.styled-table > tbody > tr:nth-of-type(odd) {
  background-color: #fff;
}

.styled-table > tbody > tr:nth-of-type(even) {
  background-color: rgb(225, 225, 225);
}

.styled-table > tbody > tr > td > input {
  border: 1px solid;
  padding: 16px;
  border-radius: 8px;
  width: 100%;
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
</style>