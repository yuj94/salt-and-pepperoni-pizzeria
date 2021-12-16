<template>
  <div class="historicalOrdersViewDiv">
    <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
    <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
    <historical-orders class="historicalOrders"></historical-orders>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import HistoricalOrders from "@/components/HistoricalOrders.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "historical-orders-view",
  components: {
    EmployeePortalBanner,
    EmployeePortalNav,
    HistoricalOrders,
    Foot,
  },
};
</script>

<style scoped>
.historicalOrdersViewDiv {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "employeePortalBanner"
    "employeePortalNav"
    "historicalOrders"
    "footer";
  gap: 24px;
}

.employeePortalBanner {
  grid-area: employeePortalBanner;
}

.employeePortalNav {
  grid-area: employeePortalNav;
}

.historicalOrders {
  grid-area: historicalOrders;
}

.foot {
  grid-area: footer;
}
</style>