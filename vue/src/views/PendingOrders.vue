<template>
  <div class="pendingOrdersViewDiv">
    <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
    <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
    <pending-orders class="pendingOrders"></pending-orders>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import PendingOrders from "@/components/PendingOrders.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "pending-orders-view",
  components: {
    EmployeePortalBanner,
    EmployeePortalNav,
    PendingOrders,
    Foot,
  },
};
</script>

<style scoped>
.pendingOrdersViewDiv {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "employeePortalBanner"
    "employeePortalNav"
    "pendingOrders"
    "footer";
}

.employeePortalBanner {
  grid-area: employeePortalBanner;
}

.employeePortalNav {
  grid-area: employeePortalNav;
}

.pendingOrders {
  grid-area: pendingOrders;
}

.foot {
  grid-area: footer;
}
</style>