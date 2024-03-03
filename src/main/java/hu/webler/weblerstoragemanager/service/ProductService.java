package hu.webler.weblerstoragemanager.service;

import hu.webler.weblerstoragemanager.entity.Product;
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

    public Product createProduct(Product.ProductCreateModel productCreateModel) {
        Product product = new Product(
                productCreateModel.getProductNumber(),
                productCreateModel.getProductName(),
                productCreateModel.getCategory(),
                productCreateModel.getDescription(),
                0 // Initial quantity set to 0
        );
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product.ProductUpdateModel updateModel) {
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

    public List<Product> getAllRawMaterial() {
        return productRepository.findAllByRawMateril(Category.ALAPANYAG);
    }

    public List<Product> getAllPurchasedItem() {
        return productRepository.findAllByPurchasedItem(Category.VÁSÁROLT_TÉTEL);
    }

    public List<Product> getAllManufacturedItem() {
        return productRepository.findAllByManufacturedItem(Category.GYÁRTOTT_TÉTEL);
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

