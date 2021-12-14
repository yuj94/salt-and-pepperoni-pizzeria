import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getAllMenuItems() {
    return http.get('/menu');
  },

  getPizzaIngredients() {
    return http.get('/ingredients')
  },

  createCustomPizza(pizza) {
    return http.post('/custom-pizza', pizza)
  }

}
