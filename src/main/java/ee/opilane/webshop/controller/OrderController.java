package ee.opilane.webshop.controller;


import ee.opilane.webshop.model.database.Order;
import ee.opilane.webshop.model.database.Person;
import ee.opilane.webshop.repository.OrderRepository;
import ee.opilane.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PersonRepository personRepository;


    @GetMapping("order")
    public ResponseEntity<List<Order>> getPersonOrders(){

      String personalCode =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

      Person person = personRepository.findById(personalCode).get();
      List<Order> orders = orderRepository.findAllByPerson(person);

      return  ResponseEntity.ok(orders);
    }
}
