<template>
  <div class='global-modal'>
    <div class="modal-inner">
      <table id="tblOrders">
      <thead>
        <tr >
          <th></th>
          <th>Order Id</th>
          <th>Item Id</th>
          <th>Item Name</th>
          <th>Quantity</th>
          <th>Price</th>
          <th><button class='toggle-modal-button' v-on:click='toggleModal()'>Return</button></th> 
        </tr>
      </thead>
      <tbody>
        <tr v-for="order in this.$store.state.pendingOrders" v-bind:key="order.orderId" >
          <td></td>
          <td>{{order.orderId}}</td>
          <td v-if="order.menuItems.menuItemId!=null">{{ order.menuItems.menuItemId }}</td>
          <td v-else>{{ order.customPizza.pizzaId}}</td>
          <td v-if="order.menuItems.itemCategory='Pizza'">{{order.menuItems.itemSize}}" {{ order.menuItems.itemName }}</td>
          <td v-else-if="order.menuItems.menuItemId=null">{{order.customPizza.itemSize}}" Custom Pizza</td>
          <td v-else>{{order.menuItems.itemName}}</td>
          <td>{{ order.menutItems.orderQuantity }}</td>
          <td v-if="order.menuItems.menuItemId!=null">{{ order.menuItems.size}}</td>
          <td v-else>{{ order.customPizza.itemSize }}</td>
          <td>{{ order.price }}</td>
        </tr>
        <!-- <tr v-for="order in this.$store.state.historicalOrders" v-bind:key="order.orderId">
          <td></td>
          <td>{{ order.orderId}}</td>
          <td v-if="order.menuItems.menuItemId!=null">{{ order.menuItems.menuItemId }}</td>
          <td v-else>{{ order.customPizza.pizzaId}}</td>
          <td v-if="order.menuItems.itemCategory='Pizza'">{{order.menuItems.itemSize}}" {{ order.menuItems.itemName }}</td>
          <td v-else-if="order.menuItems.menuItemId=null">{{order.customPizza.itemSize}}" Custom Pizza</td>
          <td v-else>{{order.menuItems.itemName}}</td>
          <td>{{ order.menutItems.orderQuantity }}</td>
          <td v-if="order.menuItems.menuItemId!=null">{{ order.menuItems.size}}</td>
          <td v-else>{{ order.customPizza.itemSize }}</td>
          <td>{{ order.price }}</td>
        </tr> -->
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
      menuItemId: '',
      menuItemName: '',
      customPizzaId: '',
      quantity: '',
      price: '',
  }
},
name: 'global-modal',
props: { 
  orderId: Number,
  // filter: Object,
  },
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
width: 200px;
height: 200px;
background-color: white;
}

.global-modal{
position: absolute;
width: 100%;
height: 100%;
background-color: rgb(0,0,0,0.5);
padding: 100px;
top:0px;
}

.modal-inner{
  background-color: white;
  width: 100%;
  height: 100%;
}

</style>