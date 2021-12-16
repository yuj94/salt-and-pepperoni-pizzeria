<template>
  <div class='global-modal'>
    <div class="modal-inner">
      <table class="styled-table">
      <thead class="tableHeader">
        <tr >
          <th>Item Id</th>
          <th>Item Name</th>
          <th>Quantity</th>
          <th>Price</th>
          <th><button class='toggle-modal-button' v-on:click='toggleModal()'>Return</button></th> 
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in this.$store.state.historicalOrders[indexOfOrderId].menuItems" v-bind:key="item.itemId">
          <td>{{ item.itemId }}</td>
          <td v-if="item.itemSize!=0">{{item.itemSize}}" {{ item.itemName }} </td>
          <td v-else>{{ item.itemName }}</td>
          <td>{{ item.orderQuantity }}</td>
          <td >{{item.price}}</td>
        </tr>
        <tr v-for="item in this.$store.state.historicalOrders[indexOfOrderId].customPizza" v-bind:key="item.itemId" >
          <td> {{item.pizzaId}}</td>
          <td>{{item.itemSize}}" Custom Pizza</td>
          <td>{{ item.orderQuantity }}</td>
          <td>{{ item.price }}</td>
        </tr>
      </tbody>
    </table>
     <div>
        <h2>Customer Name:</h2>
        <p>{{ this.$store.state.historicalOrders[indexOfOrderId].firstName }}</p>
        <p>{{ this.$store.state.historicalOrders[indexOfOrderId].lastName }}</p>
        <h2>Customer Address:</h2>
        <p>{{ this.$store.state.historicalOrders[indexOfOrderId].addressLine }}</p>
        <p>{{ this.$store.state.historicalOrders[indexOfOrderId].addressState }}</p>
        <p>{{ this.$store.state.historicalOrders[indexOfOrderId].addressCity }}</p>
        <p>{{ this.$store.state.historicalOrders[indexOfOrderId].addressZipCode }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import orderService from '@/services/OrderService.js';

export default {
  data(){
    return {
    }
  },
  computed: {
    indexOfOrderId() {
      return this.$store.state.historicalOrders.findIndex(e => e.orderId === this.orderId)
    },
  },
  name: 'global-modal',
  props: { 
    orderId: Number,
    },
  // props: ["orderId"],
  methods:{
    toggleModal(){
      this.$emit('toggle')
    },
    viewOrderDetails(orderId){ 
      orderService.getMenuItemDetails(orderId).then((response)=> {
        this.$store.commit("SET_MENU_ITEM_DETAILS", response.data);
        })
      orderService.getCustomPizzaDetails(orderId).then((response)=>{
        this.$store.commit("SET_CUSTOM_PIZZA_DETAILS", response.data);
        })
    },
  },
  created(){
    this.viewOrderDetails(this.orderId);
  }

}
</script>

<style scoped>
#tblOrders{
  width: 100%;
}

.toggle-modal-button{
    width: 100px;
    height: 50px;
    background-color: white;
}

.global-modal{
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgb(0,0,0,0.5);
    padding: 100px;
    top:0px;
    z-index: 20;
}

.modal-inner{
  background-color: white;
  width: 100%;
  height: 100%;
}

.table-header{
    align-self: left;
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