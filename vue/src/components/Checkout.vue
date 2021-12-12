<template>
  <div class="checkoutDiv">
    <form v-on:submit.prevent="" class="checkoutForm">
      <div class="nameDiv">
        <div id="firstNameDiv">
          <label for="firstName">First Name: </label>
          <input id="firstName" type="text" />
        </div>
        <div id="lastNameDiv">
          <label for="lastName"> Last Name: </label>
          <input id="lastName" type="text" />
        </div>
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
        <a id="delivery-button" href="#" v-on:click.prevent="changeDelivery">{{
          isTakeout ? "Click Here For Delivery" : "Click Here For Takeout"
        }}</a>
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
            <label for="creditCardNumber">Credit Card # </label>
            <input
              id="creditCardNumber"
              type="number"
              min="1000000000000000"
              max="9999999999999999"
            />
          </div>
          <div id="creditCardExpMonth">
            <label for="creditCardExpMonth">Ex. Month: </label>
            <input id="creditCardExpMonth" type="number" min="1" max="12" />
          </div>
          <div id="creditCardExpYear">
            <label for="creditCardExpYear">Ex. Year </label>
            <input id="creditCardExpYear" type="number" min="2021" max="2040" />
          </div>
          <div id="creditCardCcv">
            <label for="creditCardCcv">CCV </label>
            <input id="creditCardCcv" type="number" min="100" max="999" />
          </div>
        </div>
      </div>
      <input type="submit" />
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
.checkoutForm {
  padding-left: 5%;
}

.nameDiv {
  display: flex;
}
</style>