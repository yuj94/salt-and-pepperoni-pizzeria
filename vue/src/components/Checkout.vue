<template>
  <div class="checkoutCompDiv">
    <h2>Checkout</h2>
    <form v-on:submit.prevent="submitOrder" class="checkoutForm" v-if="!isCompleted">
      <h3>Contact Information</h3>
      <div id="contactInfoDiv">
        <div id="firstNameDiv">
          <label for="firstName"></label>
          <input type="text" id="firstName" placeholder="First name" v-model="order.firstName" required/>
        </div>
        <div id="lastNameDiv">
          <label for="lastName"></label>
          <input type="text" id="lastName" placeholder="Last name" v-model="order.lastName" required/>
        </div>
        <div id="emailDiv">
          <label for="email"></label>
          <input type="text" id="email" placeholder="Email" v-model="order.email" required/>
        </div>
        <div id="phoneDiv">
          <label for="phone"></label>
          <input type="text" id="phoneNumber" placeholder="Phone" v-model="order.phoneNumber" required/>
        </div>
      </div>
      <div id="deliveryOptionDiv">
        <a id="delivery-button" href="#" v-on:click.prevent="changeDelivery">{{ isTakeout ? "Click Here For Delivery" : "Click Here For Takeout" }}</a>
      </div>
      <div id="takeOutDiv" v-if="!isTakeout">
        <h3>Delivery Address</h3>
        <div id="deliveryAddressDiv">
          <div id="addressLineDiv">
            <label for="addressLine"></label>
            <input type="text" id="addressLine" placeholder="Address"  v-model="order.addressLine" required/>
          </div>
          <div id="addressCityDiv">
            <label for="addressCity"></label>
            <input type="text" id="addressCity" placeholder="City" v-model="order.addressCity" required/>
          </div>
          <div id="addressStateDiv">
            <label for="addressState"></label>
            <input type="text" id="addressState" placeholder="State"  v-model="order.addressState" required/>
          </div>
          <div id="addressZipCodeDiv">
            <label for="addressZipCode"></label>
            <input type="number" id="addressZipCode" min=00001 max=99999 placeholder="Zip code" v-model="order.addressZipCode" required/>
          </div>
        </div>
        <h3>Payment Infomation</h3>
        <div id="paymentInfoDiv">
          <div id="creditCardNumberDiv">
            <label for="creditCardNumber"></label>
            <input type="number" id="creditCardNumber" min="1000000000000000" max="9999999999999999" placeholder="Card number" v-model="order.creditCardNumber" required/>
          </div>
          <div id="creditCardCCVDiv">
            <label for="creditCardCCV"></label>
            <input type="number" id="creditCardCCV" min="100" max="999" placeholder="Security code" v-model="order.creditCardCCV" required/>
          </div>
          <div id="creditCardExpMonthDiv">
            <label for="creditCardExpMonth"></label>
            <input type="number" id="creditCardExpMonth" min="1" max="12" placeholder="Expiration month" v-model="order.creditCardExpMonth" required/>
          </div>
          <div id="creditCardExpYearDiv">
            <label for="creditCardExpYear"></label>
            <input type="number" id="creditCardExpYear" min="21" max="40" placeholder="Expiration year" v-model="order.creditCardExpYear" required/>
          </div>
        </div>
      </div>
      <input type="submit" value="Submit Order" class="checkoutSubmit"/>
    </form>
    <div class="orderSubmitted" v-else>
      <p>Thanks for your order! Your order number is: {{ this.orderId }}</p>
    </div>
  </div>
</template>

<script>
import orderService from "@/services/OrderService.js";

export default {
  name: "checkout-component",
  data() {
    return {
      orderId: '',
      isTakeout: true,
      order: {
      },
      isCompleted: false,
    };
  },
  methods: {
    changeDelivery() {
      this.isTakeout = !this.isTakeout;
    },
    submitOrder() {
      this.generateOrderInformation();
      orderService.submitOrder(this.order).then((response) => {
        this.orderId = response.data;
        this.order = {};
        this.$store.state.cart = [];
        this.isCompleted = true;
      });
    },
    generateOrderInformation() {
      this.order.isDelivery = !this.isTakeout;
      this.order.orderTotal = this.getOrderTotal();
      this.order.menuItems = this.$store.state.cart.filter(e => e.itemType == "Menu");
      this.order.customPizza = this.$store.state.cart.filter(e => e.itemType == "Custom");
    },
    getOrderTotal() {
      let totalPrice = 0;
      this.$store.state.cart.forEach((item) => {
        totalPrice += (item.price * item.orderQuantity);
      });
      return Number(totalPrice).toFixed(2);
    }
  },
};
</script>

<style scoped>
.checkoutCompDiv > h2 {
  background-color: #d20201;
  color: #fff;
  border-radius: 8px;
  text-align: center;
  padding: 16px;
}

.checkoutForm {
  background-color: #fff;
  padding: 16px;
  border-radius: 8px;
  margin-top: 16px;
}

.checkoutForm > h3 {
  margin-bottom: 16px;
}

#contactInfoDiv {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "firstName lastName"
    "email phone";
  gap: 16px;
}

#firstNameDiv {
  grid-area: firstName;
}

#lastNameDiv {
  grid-area: lastName;
}

#emailDiv {
  grid-area: email;
}

#phoneDiv {
  grid-area: phone;
}

#deliveryOptionDiv {
 margin: 16px 0;
}

#deliveryOptionDiv > a {
 color: #000;
 transition: color 0.5s;
}

#deliveryOptionDiv > a:hover {
  color: rgba(0, 0, 0, 0.5);
}

#takeOutDiv > h3 {
  margin: 16px 0;
}

#deliveryAddressDiv {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "addressLine addressLine addressLine"
    "addressCity addressState addressZipCode";
  gap: 16px;
}

#addressLineDiv {
  grid-area: addressLine;
}

#addressCityDiv {
  grid-area: addressCity;
}

#addressStateDiv {
  grid-area: addressState;
}

#addressZipCodeDiv {
  grid-area: addressZipCode;
}

#paymentInfoDiv {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "creditCardNumber creditCardNumber creditCardNumber"
    "creditCardExpMonth creditCardExpYear creditCardCCV";
  gap: 16px;
}

#creditCardNumberDiv {
  grid-area: creditCardNumber;
}

#creditCardCCVDiv {
  grid-area: creditCardCCV;
}

#creditCardExpMonthDiv {
  grid-area: creditCardExpMonth;
}

#creditCardExpYearDiv {
  grid-area: creditCardExpYear;
}

#firstNameDiv > input,
#lastNameDiv > input,
#emailDiv > input,
#phoneDiv > input,
#addressLineDiv > input,
#addressCityDiv > input,
#addressStateDiv > input,
#addressZipCodeDiv > input,
#creditCardNumberDiv > input,
#creditCardCCVDiv > input,
#creditCardExpMonthDiv > input,
#creditCardExpYearDiv > input {
  width: 100%;
  border: 1px solid #000;
  padding: 12px;
  border-radius: 8px;
}

.checkoutForm > input {
  display: flex;
  margin: 16px auto 0 auto;
}

.checkoutSubmit {
  border: none;
  padding: 16px 24px;
  border-radius: 8px;
  background-color: #d20201;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.5s;
}

.checkoutSubmit:hover {
  background-color: rgba(210, 2, 1, 0.5);
}

.orderSubmitted {
  background-color: #fff;
  padding: 16px;
  border-radius: 8px;
  margin-top: 16px;
  text-align: center;
}
</style>