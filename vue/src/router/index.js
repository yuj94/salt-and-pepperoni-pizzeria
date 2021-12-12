import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import MenuCategory from '../views/MenuCategory.vue'
import CustomPizza from '../views/CustomPizza.vue'
import PendingOrders from '../views/PendingOrders.vue'
import HistoricalOrders from '../views/HistoricalOrders.vue'
import ManageInventory from '../views/ManageInventory.vue'
import ManageMenuItems from '../views/ManageMenu.vue'
import Checkout from '../views/Checkout.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/custom-pizza",
      name: "custom-pizza",
      component: CustomPizza,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/employee-portal/pending-orders",
      name: "pending-orders",
      component: PendingOrders,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/employee-portal/historical-orders",
      name: "historical-orders",
      component: HistoricalOrders,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/employee-portal/menu-items",
      name: "manage-menu-items",
      component: ManageMenuItems,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/employee-portal/inventory",
      name: "manage-inventory",
      component: ManageInventory,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/checkout",
      name: "checkout",
      component: Checkout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/:category",
      name: "menu-category",
      component: MenuCategory,
      meta: {
        requiresAuth: false
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
