package hu.webler.weblerstoragemanager.controller;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.value.Category;
import hu.webler.weblerstoragemanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product.ProductCreateModel createModel) {
        Product product = productService.createProduct(createModel);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product.ProductUpdateModel updateModel) {
        Product updatedProduct = productService.updateProduct(id, updateModel);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/Category/ALAPANYAG")
    public ResponseEntity<List<Product>> getAllRawMaterial(@PathVariable Category ALAPANYAG) {
        List<Product> products = productService.getAllRawMaterial();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/Category/VÁSÁROLT_TÉTEL")
    public ResponseEntity<List<Product>> getAllPurchasedItem(@PathVariable Category VÁSÁROLT_TÉTEL) {
        List<Product> products = productService.getAllPurchasedItem();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/Category/GYÁRTOTT_TÉTEL")
    public ResponseEntity<List<Product>> getAllManufacturedItem(@PathVariable Category GYÁRTOTT_TÉTEL) {
        List<Product> products = productService.getAllManufacturedItem();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/by-product-number/{productNumber}")
    public ResponseEntity<Product> getProductByProductNumber(@PathVariable String productNumber) {
        Product product = productService.getProductByProductNumber(productNumber);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

