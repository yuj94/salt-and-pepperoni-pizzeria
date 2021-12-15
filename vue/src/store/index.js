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

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    currentCartItemId: 1,
    menuItems: [],
    customPizzas: [],
    cart: [],
    ingredients: [],
    categories: ['Pizzas', 'Appetizers', 'Salads', 'Desserts', 'Drinks'],
    pendingOrders: [],
    historicalOrders: [],
    orderItems: [],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
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
    },
    ADD_MENU_ITEM_TO_CART(state, data) {
      state.cart.push(data);
    },
    SET_PENDING_ORDERS(state, data) {
      state.pendingOrders = data;
    },
    SET_HISTORICAL_ORDERS(state, data) {
      state.historicalOrders = data;
    },
    REMOVE_ITEM_FROM_CART(state, data) {
      let indexOfItem = state.cart.indexOf(data);
      state.cart.splice(indexOfItem, 1);
    },
    UPDATE_MENU_ITEM(state, item) {
      let indexOfItem = state.cart.findIndex( e => 
        e.itemId === item.itemId && 
        e.itemSize === item.itemSize && 
        e.price === item.price && 
        e.itemName === item.itemName);
      state.cart.splice(indexOfItem, 1);
      state.cart.push(item);
    },
    ADD_CUSTOM_PIZZA_TO_CART(state, data) {
      state.cart.push(data);
    },
    SET_MENU_ITEM_DETAILS_PENDING(state, data) {
      state.pendingOrders = data;
    },
    SET_CUSTOM_PIZZA_DETAILS_PENDING(state, data){
      state.pendingOrders = data;
    },
    SET_ORDER_TO_COMPLETE(state, data){
      state.pendingOrders = data;
    },
    SET_ORDER_TO_NOT_COMPLETE(state, data){
      state.historicalOrders = data;
    },
    SET_MENU_ITEM_DETAILS_HISTORICAL(state, data) {
      state.historicalOrders = data;
    },
    SET_CUSTOM_PIZZA_DETAILS_HISTORICAL(state, data){
      state.historicalOrders = data;
    },
  }
})