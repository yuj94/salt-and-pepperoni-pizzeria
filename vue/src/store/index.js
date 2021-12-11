import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    menuItems: {
      itemId: '',
      itemName: '',
      itemDescription: '',
      itemCategory: '',
      price: '',
      ingredients: {},
      totalQuantity: '',
      imageUrl: '',
    },
    customPizzas: {
      ingredients: {

      }
    },
    cart: {
      menuItems: {

      },
      customPizzas: {

      },
      totalPrice: 0,
      
      // cart.menuItems.array.forEach(element => { 
      //   return element.price
      // });
    },
    ingredients: {
      ingredientId: '',
      ingredientName: '',
      ingredientType: '',
      price: '',
      orderQuantity: '',
      totalQuantity: '',
    },
    categories: ['Pizzas', 'Appetizers', 'Salads', 'Desserts', 'Drinks'],
    pendingOrders: {
      orderId: "",
      firstName: "",
      lastName: "",
      addressLine: "",
      addressState: "",
      addressCity: "",
      addressZipCode: "",
      email: "",
      phoneNumber: "",
      menuItems: {},
      customPizza: {},
      isDelivery: "",
      isCompleted: false,
      orderTotal: ""
    },
    historicalOrders: {
        orderId: "",
        firstName: "",
        lastName: "",
        addressLine: "",
        addressState: "",
        addressCity: "",
        addressZipCode: "",
        email: "",
        phoneNumber: "",
        menuItems: {},
        customPizza: {},
        isDelivery: "",
        isCompleted: true,
        orderTotal: ""
      }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_MENU_ITEMS(state, data) {
      state.menuItems = data;
    },
    SET_INGREDIENTS(state, data) {
      state.ingredients = data;
    }
  }
})
