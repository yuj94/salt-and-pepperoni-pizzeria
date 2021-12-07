<template>
  <div> <navigation></navigation>
    <menu-items></menu-items>
  </div>
</template>

<script>
import MenuItems from '@/components/MenuItems.vue';
import Navigation from '@/components/Navigation.vue';

export default {
    name: 'MenuView',
    components: {
        MenuItems,
        Navigation
    },
};
</script>

<style>
</style>