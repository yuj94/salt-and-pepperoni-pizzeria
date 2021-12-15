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
  },

  addIngredient(newIngredient) {
    return http.post('/add-ingredient', newIngredient);
  },

  addNewMenuItem(newMenuItem) {
    return http.post('/create-menu-item', newMenuItem);
  },

  updateIngredientPrice(ingredientId, price) {
    return http.put('/update/ingredient-price/'+ ingredientId, ingredientId, price);
  },

  updateIngredientQuantity(ingredientId, totalQuantity) {
    return http.put('/update/ingredient-quantity/'+ ingredientId, ingredientId, totalQuantity);
  }
}