import axios from 'axios';

// const http = axios.create({
//   baseURL: "http://localhost:8080"
// });

export default {

  getAllPendingOrders() {
    return axios.get('/order/pending');
  },

  getAllHistoricalOrders() {
    return axios.get('/order/history');
  },

  getMenuItemDetails(orderId){
    return axios.get('/order/pending/menuItem/' + orderId, orderId);
  },

  getCustomPizzaDetails(orderId){
    return axios.get('order/pending/customPizza/' + orderId, orderId);
  },

  setOrderToComplete(orderId){
    return axios.put('/order/pending/markComplete/' + orderId, orderId);
  },
  
  setOrderToNotComplete(orderId){
    return axios.put('/order/pending/markNotComplete/' + orderId);
  },

  submitOrder(order) {
    return axios.post('/checkout', order);
  }

}
