package hu.webler.weblerstoragemanager.service;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.model.ProductCreateModel;
import hu.webler.weblerstoragemanager.model.ProductUpdateModel;
import hu.webler.weblerstoragemanager.value.Category;
import hu.webler.weblerstoragemanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductCreateModel productCreateModel) {
        Product product = new Product();
        product.setProductNumber(productCreateModel.getProductNumber());
        product.setProductName(productCreateModel.getProductName());
        product.setCategory(productCreateModel.getCategory());
        product.setDescription(productCreateModel.getDescription());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductUpdateModel updateModel) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (updateModel.getProductName() != null) {
                product.setProductName(updateModel.getProductName());
            }
            if (updateModel.getDescription() != null) {
                product.setDescription(updateModel.getDescription());
            }
            return productRepository.save(product);
        } else {

            return null;
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllByQuantityGreaterThan(0);
    }

    public List<Product> getAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    public Product getProductById(Long id) {
        return productRepository.findByIdAndQuantityGreaterThan(id, 0);
    }

    public Product getProductByProductNumber(String productNumber) {
        return productRepository.findByProductNumberAndQuantityGreaterThan(productNumber, 0);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

