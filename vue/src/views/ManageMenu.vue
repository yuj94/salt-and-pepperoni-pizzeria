<template>
  <div>
      <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
      <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
      <menu-inventory></menu-inventory>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import MenuInventory from "@/components/MenuInventory.vue";

export default {
    name: "manage-menu-view",
        components: {
        EmployeePortalBanner,
        EmployeePortalNav,
        MenuInventory,
    },
}
</script>

<style>

</style>