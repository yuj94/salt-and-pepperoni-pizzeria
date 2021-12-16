<template>
  <div>
    <historical-modal v-show="showModal" v-bind:orderId="orderId" @toggle="toggleModal(orderId)"></historical-modal>
    <div class="historicalOrdersDiv">
      <table class="styled-table">
        <thead>
          <tr>
            <th></th>
            <th>Order Id</th>
            <th>Time to Fulfill</th>
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
            <td>Hrs:Mins:Secs</td>
            <td>
              <input type="text" id="firstNameFilter" v-model="filter.firstName" placeholder="First Name" />
            </td>
            <td>
              <input type="text" id="lastNameFilter" v-model="filter.lastName" placeholder="Last Name" />
            </td>
            <td>
              <input type="text" id="emailFilter" v-model="filter.email" placeholder="E-Mail" />
            </td>
            <td>
              <input type="text" id="phoneNumberFilter" v-model="filter.phoneNumber" placeholder="Phone Number" />
            </td>
            <td>
              <input type="text" id="orderTotalFilter" v-model="filter.orderTotal" placeholder="Order Total" />
            </td>
            <td></td>
          </tr>
          <tr v-for="order in this.filteredList" v-bind:key="order.orderId" v-bind:filter="filter">
            <button class="toggle-modal-button" v-on:click="toggleModal(order.orderId)">Order Details</button>
            <td class="center">{{ order.orderId }}</td>
            <td  class="center">{{order.timeElapsed.substring(1, 8)}}</td>
            <td>{{ order.firstName }}</td>
            <td>{{ order.lastName }}</td>
            <td>{{ order.email }}</td>
            <td class="center">{{ order.phoneNumber }}</td>
            <td class="center">${{ Number(order.orderTotal).toFixed(2) }}</td>
            <button type="button" class="notCompletedButton" v-on:click="markAsNotCompleted(order.orderId)">Mark Not Completed</button>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import orderService from "@/services/OrderService.js";
import HistoricalModal from "@/components/HistoricalModal.vue";

export default {
  name: "historical-orders-list",
  components: {
    HistoricalModal,
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
      showModal: false,
    };
  },
  computed: {
    filteredList() {
      return this.$store.state.historicalOrders.filter((order) => {
        return (
          order.orderId.toString().includes(this.filter.orderId.toString()) &&
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
    getHistoricalOrders() {
      orderService.getAllHistoricalOrders().then((response) => {
        this.$store.commit("SET_HISTORICAL_ORDERS", response.data);
      });
    },
    markAsNotCompleted(orderId) {
      orderService.setOrderToNotComplete(orderId).then((response) => {
        this.$store.commit("SET_ORDER_TO_NOT_COMPLETE", response.data);
        window.location.reload();
      });
    },
    toggleModal(orderId) {
      this.orderId = orderId;
      this.showModal = !this.showModal;
    },
  },
  created() {
    this.getHistoricalOrders();
  },
};
</script>

<style scoped>

.historicalOrdersDiv {
  padding: 0 24px;
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
}

.center {
  text-align: center;
}

.toggle-modal-button,
.notCompletedButton {
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
.notCompletedButton:hover {
  background-color: rgba(210, 2, 1, 0.5);
  }
</style>