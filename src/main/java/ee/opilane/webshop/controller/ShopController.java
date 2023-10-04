package ee.opilane.webshop.controller;

import ee.opilane.webshop.model.database.Product;
import ee.opilane.webshop.model.database.Shop;
import ee.opilane.webshop.repository.ShopRepository;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {

    @Autowired
    ShopRepository shopRepository;


    @GetMapping("shop")
    public ResponseEntity<List<Shop>> getShops(List list) {
        return ResponseEntity.ok().body(shopRepository.findAll());
    }

    @GetMapping("shop/{id}")
    public ResponseEntity<Shop> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(shopRepository.findById(id).get());
    }


    @DeleteMapping("shop/{id}")
    public ResponseEntity<List<Shop>> deleteShop(@PathVariable Long id) {
        shopRepository.deleteById(id);
        return ResponseEntity.ok().body(shopRepository.findAll());
    }

    @PostMapping("shop")
    public ResponseEntity<List<Shop>> addShop(@RequestBody Shop shop) {
        if (shop.getId() == null || shopRepository.findById(shop.getId()).isEmpty()) {
            shopRepository.save(shop);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(shopRepository.findAll());
    }

    @PutMapping("shop")
    public ResponseEntity<List<Shop>> editShop (@RequestBody Shop shop) {
        if (shopRepository.findById(shop.getId()).isPresent()) {
            shopRepository.save(shop);
        }
        return ResponseEntity.ok().body(shopRepository.findAll());
    }

}
