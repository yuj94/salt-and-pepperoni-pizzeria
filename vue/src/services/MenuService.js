import axios from 'axios';

// const http = axios.create({
//   baseURL: "http://localhost:8080"
// });

export default {

  getAllMenuItems() {
    return axios.get('/menu');
  },

  getPizzaIngredients() {
    return axios.get('/ingredients')
  },

  createCustomPizza(pizza) {
    return axios.post('/custom-pizza', pizza)
  },

  addIngredient(newIngredient) {
    return axios.post('/add-ingredient', newIngredient);
  },

  addNewMenuItem(newMenuItem) {
    return axios.post('/create-menu-item', newMenuItem);
  },

  updateIngredientPrice(ingredientId, price) {
    return axios.put('/update/ingredient-price/'+ ingredientId, ingredientId, price);
  },

  updateIngredientQuantity(ingredientId, totalQuantity) {
    return axios.put('/update/ingredient-quantity/'+ ingredientId, ingredientId, totalQuantity);
  }
}