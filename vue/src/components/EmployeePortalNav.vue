<template>
  <div class="employeePortalNavDiv">
    <router-link v-bind:to="{name: 'pending-orders'}"><h2>Pending Orders</h2></router-link>
    <router-link v-bind:to="{name: 'historical-orders'}"><h2>Historical Orders</h2></router-link>
    <router-link v-bind:to="{name: 'manage-inventory'}"><h2>Inventory</h2></router-link>
    <router-link v-bind:to="{name: 'manage-menu-items'}"><h2>Menu Items</h2></router-link>
    <router-link v-bind:to="{name: 'register'}"><h2>Register Employee</h2></router-link>
    <router-link v-bind:to="{name: 'logout'}"><h2>Logout</h2></router-link>
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
    flex-direction: row;
    justify-content: space-evenly;
    text-align: center;
    padding-left: 24px;
}

.employeePortalNavDiv > a {
  text-decoration: none;
  color: #000;
  transition: color 0.5s;
}

.employeePortalNavDiv > a:hover {
  color: #d20201;
}

.employeePortalNavDiv > a > h2 {
  padding: 16px;
  margin: 16px 0;
}

.employeePortalNavDiv > a {
  text-decoration: none;
  color: #000;
  transition: color 0.5s;
}

.employeePortalNavDiv > a:hover {
  color: #d20201;
}

.employeePortalNavDiv > a > h2 {
  padding: 16px;
}

.router-link-exact-active {
  color: #d20201 !important;
}

.router-link-exact-active > h2 {
  background-color: #fff;
  border-radius: 8px;
}
</style>