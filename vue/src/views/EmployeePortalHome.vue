<template>
  <div class="employeePortalDiv">
      <employee-portal-banner class="employeePortalBanner"></employee-portal-banner>
      <employee-portal-nav class="employeePortalNav"></employee-portal-nav>
  </div>
</template>

<script>
import EmployeePortalBanner from "@/components/EmployeePortalBanner.vue";
import EmployeePortalNav from "@/components/EmployeePortalNav.vue";

export default {
    name: "employee-portal-home-view",
        components: {
        EmployeePortalBanner,
        EmployeePortalNav,
    },
}
</script>

<style>
.employeePortalDiv {
    display: grid;
    grid-auto-columns: 1fr 1fr;
    grid-template-areas: 
        "banner banner"
        "nav nav";
    text-align: center;
}

.employeePortalBanner {
    grid-area: banner
}


.employeePortalNav {
    grid-area: nav;
}
</style>