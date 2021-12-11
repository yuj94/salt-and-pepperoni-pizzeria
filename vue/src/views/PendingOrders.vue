<template>
  <div>
      <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
      <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
      <pending-orders class="pendingOrders"></pending-orders>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import PendingOrders from "@/components/PendingOrders.vue";

export default {
    name: "pending-orders-view",
        components: {
        EmployeePortalBanner,
        EmployeePortalNav,
        PendingOrders,
    },
}
</script>

<style>

</style>