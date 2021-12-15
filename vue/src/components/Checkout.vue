<template>
  <div class="checkoutCompDiv">
    <h2>Checkout</h2>
    <form v-on:submit.prevent="" class="checkoutForm">
      <div id="firstNameDiv">
        <label for="firstName">First Name: </label>
        <input id="firstName" type="text" />
      </div>
      <div id="lastNameDiv">
        <label for="lastName"> Last Name: </label>
        <input id="lastName" type="text" />
      </div>
      <div id="email">
        <label for="email">Email: </label>
        <input id="email" type="text" />
      </div>
      <div id="phoneNumber">
        <label for="phoneNumber">Phone Number: </label>
        <input id="phoneNumber" type="text" />
      </div>
      <div id="deliveryOptionDiv">
        <a id="delivery-button" href="#" v-on:click.prevent="changeDelivery">{{ isTakeout ? "Click Here For Delivery" : "Click Here For Takeout" }}</a>
      </div>
      <div id="deliveryDetailsDiv" v-if="!isTakeout">
        <div id="addressLine">
          <label for="addressLine">Address: </label>
          <input id="addressLine" type="text" />
        </div>
        <div id="addressCity">
          <label for="addressCity">City: </label>
          <input id="addressCity" type="text" />
        </div>
        <div id="addressState">
          <label for="addressState">State: </label>
          <input id="addressState" type="text" />
        </div>
        <div id="addressZipCode">
          <label for="addressZipCode">Zip Code: </label>
          <input id="addressZipCode" type="number" min=00001 max=99999/>
        </div>
        <div class="creditCardInformation">
          <div id="creditCardNumber">
            <label for="creditCardNumber">Credit Card: </label>
            <input id="creditCardNumber" type="number" min="1000000000000000" max="9999999999999999" />
          </div>
          <div id="creditCardExpMonth">
            <label for="creditCardExpMonth">Ex. Month: </label>
            <input id="creditCardExpMonth" type="number" min="1" max="12" />
          </div>
          <div id="creditCardExpYear">
            <label for="creditCardExpYear">Ex. Year: </label>
            <input id="creditCardExpYear" type="number" min="2021" max="2040" />
          </div>
          <div id="creditCardCcv">
            <label for="creditCardCcv">CCV: </label>
            <input id="creditCardCcv" type="number" min="100" max="999" />
          </div>
        </div>
      </div>
      <input type="submit" value="Submit Order" class="checkoutSubmit" />
    </form>
  </div>
</template>

<script>
export default {
  name: "checkout-component",
  data() {
    return {
      isTakeout: true,
    };
  },
  methods: {
    changeDelivery() {
      this.isTakeout = !this.isTakeout;
    },
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
  width: 100;
}

.checkoutForm > div,
#deliveryDetailsDiv > div,
.creditCardInformation > div {
  margin-bottom: 16px;
}

.checkoutForm > div > input,
#deliveryDetailsDiv > div > input,
.creditCardInformation > div > input {
  width: 25%;
  border: 1px solid #000;
  padding: 6px;;
  border-radius: 8px;
}

#deliveryOptionDiv > a {
 color: #000;
 transition: color 0.5s;
}

#deliveryOptionDiv > a:hover {
  color: rgba(0, 0, 0, 0.5);
}

.checkoutForm > input {
  display: flex;
  margin: 0 auto;
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
</style>