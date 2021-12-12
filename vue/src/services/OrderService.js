import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getAllPendingOrders() {
    return http.get('/order/pending');
  },

  getAllHistoricalOrders() {
    return http.get('/order/history')
  }

}
