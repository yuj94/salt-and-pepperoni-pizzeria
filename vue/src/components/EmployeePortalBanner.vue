<template>
  <div class="employeePortalBannerDiv bannerImg">
    <h1>Salt & Pepperoni Pizzeria's Employee Portal</h1>
    <div class="employeePortalLinks">
      <router-link v-bind:to="{ name: 'register' }">Register</router-link>
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "employee-portal-banner",
};
</script>

<style scoped>
.employeePortalBannerDiv {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas: "logo logo links";
  height: 96px;
  padding: 0 24px;
  z-index: 10;
}

.employeePortalBannerDiv > h1 {
  grid-area: logo;
  color: #fff;
  display: flex;
  align-items: center;
}

.employeePortalLinks {
  grid-area: links;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  text-align: right;
}

.employeePortalLinks > a {
  color: #fff;
  text-decoration: none;
  font-size: 20px;
  font-weight: bold;
  transition: color 0.5s;
}

.employeePortalLinks > a:hover {
  color: rgba(255, 255, 255, 0.5);
}

.employeePortalLinks > a:nth-child(1) {
  margin-right: 24px;
}

.bannerImg {
  background: url(https://images.unsplash.com/photo-1630443876697-e0d2faac7b51?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 50% 60%;
}
</style>