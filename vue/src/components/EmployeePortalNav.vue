<template>
  <div class="employeePortalNavDiv">
    <router-link v-bind:to="{ name: 'pending-orders' }"><h3>Pending Orders</h3></router-link>
    <router-link v-bind:to="{ name: 'historical-orders' }"><h3>Historical Orders</h3></router-link>
    <!-- <router-link v-bind:to="{ name: 'manage-inventory' }"><h3>Inventory</h3></router-link>
    <router-link v-bind:to="{ name: 'manage-menu-items' }"><h3>Menu Items</h3></router-link> -->
  </div>
</template>

<script>
export default {
  name: "employee-portal-nav",
};
</script>

<style scoped>
.employeePortalNavDiv {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  /* width: 100%;
  position: fixed;
  margin-top: 96px;
  padding: 24px;
  background-color: #f6f2ed; */
  z-index: 5;
}

.employeePortalNavDiv > a {
  text-decoration: none;
  color: #000;
  transition: color 0.5s;
}

.employeePortalNavDiv > a:hover {
  color: #d20201;
}

.employeePortalNavDiv > a > h3 {
  padding: 16px;
}

.router-link-exact-active {
  color: #d20201 !important;
}

.router-link-exact-active > h3 {
  background-color: #fff;
  border-radius: 8px;
}
</style>