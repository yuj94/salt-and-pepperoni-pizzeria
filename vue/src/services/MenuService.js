import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getAllMenuItems() {
    return http.get('/menu');
  },

  getPizzaItems() {
    return http.get('/menu/pizzas');
  },

  getDrinkItems() {
    return http.get('/menu/drinks');
  },

  getAppetizerItems() {
    return http.get('/menu/appetizers');
  },
  
  getSaladItems() {
    return http.get('/menu/salads');
  },

  getDessertItems() {
    return http.get('/menu/desserts');
  }

}
