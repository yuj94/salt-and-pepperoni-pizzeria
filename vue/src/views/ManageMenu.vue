<template>
  <div class="manageMenuViewDiv">
    <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
    <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
    <menu-inventory class="menuInventory"></menu-inventory>
    <foot class="foot"></foot>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";
import MenuInventory from "@/components/MenuInventory.vue";
import Foot from "@/components/Foot.vue";

export default {
  name: "manage-menu-view",
  components: {
    EmployeePortalBanner,
    EmployeePortalNav,
    MenuInventory,
    Foot,
  },
};
</script>

<style scoped>
.manageMenuViewDiv {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "employeePortalBanner"
    "employeePortalNav"
    "menuInventory"
    "footer";
}

.employeePortalBanner {
  grid-area: employeePortalBanner;
}

.employeePortalNav {
  grid-area: employeePortalNav;
}

.menuInventory {
  grid-area: menuInventory;
}

.foot {
  grid-area: footer;
}
</style>