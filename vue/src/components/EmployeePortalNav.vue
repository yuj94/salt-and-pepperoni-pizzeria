<template>
  <div class="employeePortalNavDiv">
    <router-link v-bind:to="{name: 'pending-orders'}"><h2>Pending Orders</h2></router-link>
    <router-link v-bind:to="{name: 'historical-orders'}"><h2>Historical Orders</h2></router-link>
    <router-link v-bind:to="{name: 'manage-inventory'}"><h2>Inventory</h2></router-link>
    <router-link v-bind:to="{name: 'manage-menu-items'}"><h2>Menu Items</h2></router-link>
    <router-link v-bind:to="{name: 'logout'}"><h2>Logout</h2></router-link>
  </div>
</template>

<script>
export default {
  name: "employee-portal-nav",
};
</script>

<style scoped>
.employeePortalNav {
    display: flex;
    flex-direction: row;
    justify-content: center;
}

h2 {
    padding: 1em;
    margin: 1em;
    color: #d20201;
    border-radius: 10px;
}
</style>