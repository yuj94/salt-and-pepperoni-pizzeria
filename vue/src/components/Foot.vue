<template>
  <div class="footerDiv">
    <div class="homepageFooter" v-if="isHomePage || isLoginPage">
      <p>
        &copy;2021 | 
        <a href="https://www.linkedin.com/in/andrew-pero-b652871b4/" target="_blank">Andrew Pero</a> | 
        <a href="https://www.linkedin.com/in/yujackie/" target="_blank">Jackie Yu</a> | 
        <a href="https://www.linkedin.com/in/justinparker9//" target="_blank">Justin Parker</a> | 
        <a href="https://www.linkedin.com/in/timothyshortess/" target="_blank">Timothy Shortess</a> | 
        Photos from <a href="https://unsplash.com/" target="_blank">Unsplash</a>, <a href="https://www.papajohns.com/" target="_blank">Papa John's Pizza</a>, <a href="https://www.amazon.com/" target="_blank">Amazon</a> | 
        All rights reserved
      </p>
    </div>
    <div class="nonHomepageFooter" v-else>
      <p>
        &copy;2021 | 
        <a href="https://www.linkedin.com/in/andrew-pero-b652871b4/" target="_blank">Andrew Pero</a> | 
        <a href="https://www.linkedin.com/in/yujackie/" target="_blank">Jackie Yu</a> | 
        <a href="https://www.linkedin.com/in/justinparker9//" target="_blank">Justin Parker</a> | 
        <a href="https://www.linkedin.com/in/timothyshortess/" target="_blank">Timothy Shortess</a> | 
        Photos from <a href="https://unsplash.com/" target="_blank">Unsplash</a>, <a href="https://www.papajohns.com/" target="_blank">Papa John's Pizza</a>, <a href="https://www.amazon.com/" target="_blank">Amazon</a> | 
        All rights reserved
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "foot",
  computed: {
    isHomePage() {
      if (this.$route.path == "/") {
        return true;
      } else {
        return false;
      }
    },
    isLoginPage() {
      if (this.$route.path == "/login") {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.footerDiv {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
}

.homepageFooter {
  background-color: rgba(0, 0, 0, 0.75);
  padding: 16px;
  border-radius: 8px;
  color: #fff;
}

.homepageFooter > p > a {
  color: #fff;
  transition: color 0.5s;
}

.homepageFooter > p > a:hover {
  color: rgba(255, 255, 255, 0.5);
}

.nonHomepageFooter {
    color: #000;
}

.nonHomepageFooter > p > a {
    color: #000;
    transition: color 0.5s;
}

.nonHomepageFooter > p > a:hover {
  color: rgba(0, 0, 0, 0.5);
}
</style>