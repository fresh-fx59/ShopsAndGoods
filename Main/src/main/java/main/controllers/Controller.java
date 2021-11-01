package main.controllers;

import main.dao.ProductDao;
import main.dao.ShopDao;
import main.model.Product;
import main.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ProductDao productDao;

    @PostMapping("/shops/")
    public ResponseEntity<?> addShop(Shop shop) {
        Optional<Shop> optionalShop = Optional.ofNullable(shopDao.get(shop.getName()));
        if (optionalShop.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Shop " +
                    shop.getName() + " already exists.");
        }
        shopDao.save(shop);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "New Shop successfully created!");
    }

    @GetMapping("/shops/")
    public ResponseEntity<?> getShops() {
        StringBuilder result = new StringBuilder();
        shopDao.getAll().forEach(s -> {
            result.append(s.toString());
        });
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @GetMapping("/shops/{name}")
    public ResponseEntity<?> getShop(@PathVariable String name) {
        return shopDao.get(name) == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't get Shop " +
                        name + " because it doesn't exist.") :
                new ResponseEntity<>(shopDao.get(name), HttpStatus.OK);
    }

    @PostMapping("/products/")
    public ResponseEntity<?> addProduct(Product product) {
        Optional<Shop> optionalShop = Optional.ofNullable(shopDao.get(product.getName()));
        if (optionalShop.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product " +
                    product.getName() + " already exists.");
        }
        productDao.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "New Product successfully created!");
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name) {
        return productDao.get(name) == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't get Product " +
                        name + " because it doesn't exist.") :
                new ResponseEntity<>(productDao.get(name), HttpStatus.OK);
    }

    @PostMapping("/addproducttoshop/")
    public ResponseEntity<?> addProductToShop(HttpServletRequest request) {
        String shopName = request.getParameter("shopName");
        String productName = request.getParameter("productName");
        Optional<Shop> shopOptional = Optional.ofNullable(shopDao.get(shopName));
        if (!shopOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't update Shop " +
                    shopName + " because it doesn't exist.");
        }
        Shop shopToUpdate = shopOptional.get();

        List<String> productsList = new ArrayList<>();
        if (!(shopToUpdate.getProducts() == null)) {
            productsList = shopToUpdate.getProducts();
        }
        productsList.add(productName);
        shopToUpdate.setProducts(productsList);
        shopDao.save(shopToUpdate);
        return new ResponseEntity<>(shopToUpdate, HttpStatus.OK);
    }

    @GetMapping("/showstatistics/")
    public String showStatistics() {
        return "Total products: " + productDao.count() + "\n" +
                "Average Product price is " + String.format("%.2f", productDao.getAvg()) + "\n" +
                "The cheapest product costs " + productDao.getCheapest().getPrice() + "\n" +
                "The most expensive product costs " + productDao.getMostExpensive().getPrice() + "\n" +
                "100+ products: " + productDao.countProductsPriceGreaterThan(100);
    }

    @GetMapping("/avg-products-price/")
    public ResponseEntity<?> getAvgProductsPrice() {
        return new ResponseEntity<>(productDao.getAvg(), HttpStatus.OK);
    }

    @GetMapping("/cheapest-product/")
    public ResponseEntity<?> getCheapestProduct() {
        return new ResponseEntity<>(productDao.getCheapest(), HttpStatus.OK);
    }

    @GetMapping("/most-expensive-product/")
    public ResponseEntity<?> getMostExpensiveProduct() {
        return new ResponseEntity<>(productDao.getMostExpensive(), HttpStatus.OK);
    }

    @GetMapping("/get-products-price-greater-than-100/")
    public ResponseEntity<?> count100plusProducts() {
        return new ResponseEntity<>(productDao.countProductsPriceGreaterThan(100),
                HttpStatus.OK);
    }

}
