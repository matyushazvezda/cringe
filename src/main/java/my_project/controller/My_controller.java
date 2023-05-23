/*package my_project.controller;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


import my_project.model.Categories;
import my_project.model.Dishes;
import my_project.model.Orders;
import my_project.model.Restaurants;
import my_project.model.Users;
import my_project.model.addDishes;
import my_project.repository.Categories_repository;
import my_project.repository.My_repository;
import my_project.repository.Repository_Dishes;
import my_project.repository.Repository_Orders;
import my_project.repository.Repository_Users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;


@Controller
@CrossOrigin(origins="*")
public class My_controller {
    @Autowired
    private My_repository repository;
    @Autowired
    private Repository_Dishes repository_dishes;
    @Autowired
    private Categories_repository repository_categories;
    @Autowired
    private Repository_Users repository_users;
    @Autowired
    private Repository_Orders repository_orders;

    //Рестораны
    @GetMapping("/")
    public ResponseEntity<?> home() {
        System.out.println(123);
         List<Restaurants> restaurants = repository.findAll();
         return ResponseEntity.ok(restaurants);}
        
    @PostMapping("/")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurants restaurant) {
            Restaurants savedRestaurant = repository.save(restaurant);
            return ResponseEntity.ok(savedRestaurant);
         }

        @GetMapping("{id}")
         public ResponseEntity<Restaurants> getRestaurant(@PathVariable Integer id) {
			 Restaurants rest = repository.findById(id).orElse(null);
            return ResponseEntity.ok(rest);
         }

        @PutMapping("{id}")
         public ResponseEntity<Restaurants> updateRestaurant(@PathVariable Integer id, @RequestBody Restaurants restaurantDettails) {
            Restaurants updateRest=repository.findById(id).orElse(null);
            updateRest.setName(restaurantDettails.getName());
            updateRest.setAddress(restaurantDettails.getAddress());
            updateRest.setCuisine(restaurantDettails.getCuisine());
            updateRest.setOpening_hours(restaurantDettails.getOpening_hours());
            updateRest.setPhone(restaurantDettails.getPhone());
            repository.save(updateRest);
            return ResponseEntity.ok(updateRest);

         }
         
        @DeleteMapping("{id}")
        public ResponseEntity<HttpStatus> deleteRestaurant(@PathVariable Integer id){
        Restaurants delRest=repository.findById(id).orElse(null);
        repository.delete(delRest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        //Блюда

        @GetMapping("/dishes/{id}")
        public ResponseEntity<?> getAllDishes(@PathVariable Integer id) {
            List<Dishes> dishes = repository_dishes.getAllDishesfromRestaurant(id);
            return ResponseEntity.ok(dishes);}

         @PutMapping("/dishes_update/{id}")
         public ResponseEntity<Dishes> updateDishes(@PathVariable Integer id, @RequestBody Dishes DishesDetail) {
            Dishes updateDishes=repository_dishes.findById(id).orElse(null);
            updateDishes.setTitle(DishesDetail.getTitle());
            updateDishes.setDescription(DishesDetail.getDescription());
            updateDishes.setPrice(DishesDetail.getPrice());
            repository_dishes.save(updateDishes);
            return ResponseEntity.ok(updateDishes);

         }

         @DeleteMapping("/dishes_delete/{id}")
        public ResponseEntity<HttpStatus> deleteDishes(@PathVariable Integer id){
        Dishes delDishes=repository_dishes.findById(id).orElse(null);
        repository_dishes.delete(delDishes);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PostMapping("/dishes_add")
        @Transactional
      public ResponseEntity<?> addDish(@RequestBody addDishes addDishes ) {
         System.out.println(addDishes);
         Categories cat = repository_categories.findById(addDishes.getId_category()).orElse(null);;
         Restaurants rest = repository.findById(addDishes.getId_restaurants()).orElse(null);
         Dishes createddihes = new Dishes();
         createddihes.setDescription(addDishes.getDescription());
         createddihes.setId_category(cat);
         createddihes.setId_restaurants(rest);
         createddihes.setPrice(addDishes.getPrice());
         createddihes.setTitle(addDishes.getTitle());
         System.out.println(createddihes);
         repository_dishes.save(createddihes);
         return ResponseEntity.ok(createddihes);
}


      @GetMapping("/dishes_from_id/{id}")
        public ResponseEntity<Dishes> getDishesFromId(@PathVariable Integer id){
        Dishes dishes = repository_dishes.findById(id).orElse(null);
        return ResponseEntity.ok(dishes);
        }



        //Категории
        @GetMapping("/Categories")
    public ResponseEntity<?> AllCategories() {
         List<Categories> categories = repository_categories.findAll();
         return ResponseEntity.ok(categories);}

         @PostMapping("/Categories_add")
    public ResponseEntity<?> addCategories(@RequestBody Categories categories) {
            Categories savedCategories = repository_categories.save(categories);
            return ResponseEntity.ok(savedCategories);
         }

        @GetMapping("/Categories/{id}")
         public ResponseEntity<Categories> getCategories(@PathVariable Integer id) {
			 Categories categories = repository_categories.findById(id).orElse(null);
            return ResponseEntity.ok(categories);
         }

        @PutMapping("/Categories_update/{id}")
         public ResponseEntity<Categories> updateCategories(@PathVariable Integer id, @RequestBody Categories categories) {
            Categories updateCategories=repository_categories.findById(id).orElse(null);
            updateCategories.setTitle(categories.getTitle());
            repository_categories.save(updateCategories);
            return ResponseEntity.ok(updateCategories);

         }
         
        @DeleteMapping("/Categories_delete/{id}")
        public ResponseEntity<HttpStatus> deleteCategories(@PathVariable Integer id){
        Categories delCategories=repository_categories.findById(id).orElse(null);
        repository_categories.delete(delCategories);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
         //юзеры
        @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
            Users userr = repository_users.save(user);
            return ResponseEntity.ok(userr);
         }

        @PostMapping("/login")
        public ResponseEntity<?> checkUser(@RequestBody Users user) {
         String password = user.getPassword();
         String login = user.getLogin();
         Users userr = repository_users.checkUserForParam(login, password);
         Integer check_User = (userr != null) ? userr.getId() : 0;
         return ResponseEntity.ok(check_User);
      }

      @GetMapping("/profile/{id}")
         public ResponseEntity<Users> getUserFromId(@PathVariable Integer id) {
			 Users user = repository_users.findById(id).orElse(null);
            return ResponseEntity.ok(user);
         }

         @PutMapping("/profile/{id}")
         public ResponseEntity<Users> updateUser( @RequestBody Users user) {
            Users updateUser=repository_users.findById(user.getId()).orElse(null);
            updateUser.setName(user.getName());
            updateUser.setAddress(user.getAddress());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setPhone(user.getPhone());
            repository_users.save(updateUser);
            return ResponseEntity.ok(updateUser);

         }

         //заказы

         @PostMapping("/orders")
        public ResponseEntity<?> createOrder(@RequestBody List<Orders> order) {
         List<Orders> savedOrder = repository_orders.saveAll(order);
         return ResponseEntity.ok(savedOrder);
      }

}*/