package hu.webler.weblerstoragemanager.controller;

import hu.webler.weblerstoragemanager.model.ProductCreateModel;
import hu.webler.weblerstoragemanager.model.ProductModel;
import hu.webler.weblerstoragemanager.model.ProductUpdateModel;
import hu.webler.weblerstoragemanager.value.Category;
import hu.webler.weblerstoragemanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductCreateModel createModel) {
        return ResponseEntity.status(201).body(productService.createProduct(createModel));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateModel updateModel) {
       return ResponseEntity.status(200).body(productService.updateProduct(id, updateModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<ProductModel>> getAllByCategory(@PathVariable Category category) {
        return ResponseEntity.status(200).body(productService.getAllByCategory(category));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProductByIdAndHasItemOnStock(@PathVariable Long id) {
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }

    @GetMapping("/products/by-product-number/{productNumber}")
    public ResponseEntity<ProductModel> getProductByProductNumber(@PathVariable String productNumber) {
       return ResponseEntity.status(200).body(productService.getProductByProductNumber(productNumber));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(204).body(null);
    }
}
