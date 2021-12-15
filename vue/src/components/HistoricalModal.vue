<template>
  <div class='global-modal'>
    <div class="modal-inner">
      <table id="tblOrders">
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

<style>
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

</style>