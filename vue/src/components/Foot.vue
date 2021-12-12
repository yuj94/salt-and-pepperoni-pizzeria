<template>
  <div class="footerDiv">
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
</template>

<script>
export default {
  name: "foot",
};
</script>

<style scoped>
.footerDiv {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 75px;
}

.footerDiv > p {
  color: #fff;
  text-align: center;
}

.footerDiv > p > a {
  text-decoration: none;
  color: #fff;
}
</style>