<template>
  <div class="employeePortalBannerDiv bannerImg">
    <h1>Salt & Pepperoni Pizzeria's Employee Portal</h1>
  </div>
</template>

<script>
export default {
  name: "employee-portal-banner",
};
</script>

<style>
.employeePortalBannerDiv {
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  height: 96px;
  width: 100vw;
  position: fixed;
  z-index: 10;
}

.bannerImg {
  background: url(https://images.unsplash.com/photo-1630443876697-e0d2faac7b51?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 50% 60%;
}
</style>