<template>
  <div>
      <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
      <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
      <ingredient-inventory></ingredient-inventory>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import IngredientInventory from "@/components/IngredientInventory.vue";

export default {
    name: "manage-inventory-view",
        components: {
        EmployeePortalBanner,
        EmployeePortalNav,
        IngredientInventory,
    },
}
</script>

<style>

</style>