<template>
  <div>
    <div class="categoryHeaderDiv" >
      <!-- <h2>{{ menuObj.itemCategory}}</h2> -->
        <h2 id="itemName">{{ menuObj.itemName }}</h2>
        <h2 id="itemPrice">${{ menuObj.price }}</h2>
          <form action="">
            
            <button type="button" class="cartButton">Add to Cart</button>
          </form>
        <p id="itemDescription">{{ menuObj.itemDescription }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "menu-details",
  props: ["menuObj"],
};
</script>

<style scoped>

.categoryHeaderDiv {
  /* border: solid 1px navy; */
  margin: none;
  display: grid;
  grid-template-columns: 6fr 1fr;
  grid-template-areas: 
    "name price"
    "description cartButton";
}

.cartButton {
  grid-area: cartButton;
  justify-content: center;
}

#itemPrice {
  grid-area: price;
  align-items: center;
}

#itemName {
  grid-area: name;
}

#itemDescription {
  grid-area: description;
}

</style>