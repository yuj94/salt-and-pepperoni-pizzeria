import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Menu from '../views/Menu.vue'
import PizzasMenu from '../views/PizzasMenu.vue'
import DrinksMenu from '../views/DrinksMenu.vue'
import AppetizersMenu from '../views/AppetizersMenu.vue'
import DessertsMenu from '../views/DessertsMenu.vue'
import SaladsMenu from '../views/SaladsMenu.vue'


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
        requiresAuth: false
      }
    },
    {
      path: "/menu",
      name: "menu",
      component: Menu,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/menu/pizzas",
      name: "pizzas-menu",
      component: PizzasMenu,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/menu/drinks",
      name: "drinks-menu",
      component: DrinksMenu,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/menu/appetizers",
      name: "appetizers-menu",
      component: AppetizersMenu,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/menu/desserts",
      name: "desserts-menu",
      component: DessertsMenu,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/menu/salads",
      name: "salads-menu",
      component: SaladsMenu,
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
