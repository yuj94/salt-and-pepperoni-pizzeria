import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getAllPendingOrders() {
    return http.get('/order/pending');
  },

  getAllHistoricalOrders() {
    return http.get('/order/history');
  },

  getMenuItemDetails(orderId){
    return http.get('/order/pending/menuItem/' + orderId);
  },

  getCustomPizzaDetails(orderId){
    return http.get('order/pending/customPizza/' + orderId );
  },

  setOrderToComplete(orderId){
    return http.put('/order/pending/markComplete/' + orderId);
  },
  
  setOrderToNotComplete(orderId){
    return http.put('/order/pending/markNotComplete/' + orderId)
  }
}
