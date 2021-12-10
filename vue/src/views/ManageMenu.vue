<template>
  <div>
      <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
      <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";

export default {
    name: "manage-menu-view",
        components: {
        EmployeePortalBanner,
        EmployeePortalNav,
    },
}
</script>

<style>

</style>