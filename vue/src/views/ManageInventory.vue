<template>
  <div class="manageInventoryViewDiv">
    <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
    <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
    <ingredient-inventory class="ingredientInventory"></ingredient-inventory>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import IngredientInventory from "@/components/IngredientInventory.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "manage-inventory-view",
  components: {
    EmployeePortalBanner,
    EmployeePortalNav,
    IngredientInventory,
    Foot,
  },
};
</script>

<style scoped>
.manageInventoryViewDiv {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "employeePortalBanner"
    "employeePortalNav"
    "ingredientInventory"
    "footer";
}

.employeePortalBanner {
  grid-area: employeePortalBanner;
}

.employeePortalNav {
  grid-area: employeePortalNav;
}

.ingredientInventory {
  grid-area: ingredientInventory;
}

.foot {
  grid-area: footer;
}
</style>