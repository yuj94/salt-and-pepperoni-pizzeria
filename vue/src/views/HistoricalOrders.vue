<template>
  <div>
      <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
      <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
      <historical-orders class="historicalOrders"></historical-orders>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import HistoricalOrders from "@/components/HistoricalOrders.vue";

export default {
    name: "historical-orders-view",
        components: {
        EmployeePortalBanner,
        EmployeePortalNav,
        HistoricalOrders,
    },
}
</script>

<style>

</style>