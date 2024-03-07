package hu.webler.weblerstoragemanager.controller;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.model.ProductCreateModel;
import hu.webler.weblerstoragemanager.model.ProductUpdateModel;
import hu.webler.weblerstoragemanager.value.Category;
import hu.webler.weblerstoragemanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateModel createModel) {
        Product product = productService.createProduct(createModel);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateModel updateModel) {
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
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getAllByCategory(@PathVariable Category category) {
        List<Product> products = productService.getAllByCategory(category);
        List<Product> filteredProducts = products.stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
        return ResponseEntity.status(200).body(filteredProducts);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.status(200).body(product);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/products/by-product-number/{productNumber}")
    public ResponseEntity<Product> getProductByProductNumber(@PathVariable String productNumber) {
        Product product = productService.getProductByProductNumber(productNumber);
        if (product != null) {
            return ResponseEntity.status(200).body(product);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(204).body(null);
    }
}

