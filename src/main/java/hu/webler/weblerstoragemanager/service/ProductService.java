package hu.webler.weblerstoragemanager.service;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.model.ProductCreateModel;
import hu.webler.weblerstoragemanager.model.ProductModel;
import hu.webler.weblerstoragemanager.model.ProductUpdateModel;
import hu.webler.weblerstoragemanager.util.Mapper;
import hu.webler.weblerstoragemanager.value.Category;
import hu.webler.weblerstoragemanager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductModel createProduct(ProductCreateModel productCreateModel) {
        return Mapper.mapProductEntityToProductModel(productRepository
                .save(Mapper.mapProductCreateModelToProductEntity(productCreateModel)));
    }

    public ProductModel updateProduct(Long id, ProductUpdateModel updateModel) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Product not exists with id: %s, cannot update", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
        existingProduct.setProductName(updateModel.getProductName());
        existingProduct.setDescription(updateModel.getDescription());
        return Mapper.mapProductEntityToProductModel(productRepository.save(existingProduct));
        }

    public List<ProductModel> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(Mapper::mapProductEntityToProductModel)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getAllByCategory(Category category) {
        return productRepository.findAll()
                .stream()
                .map(Mapper::mapProductEntityToProductModel)
                .filter(c -> c.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public ProductModel getProductById(Long id) {
        return Mapper.mapProductEntityToProductModel(productRepository.findByIdAndHasItemOnStock(id,0)
                .orElseThrow(() ->{
                    String message = String.format("Product with id: %d not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }

    public ProductModel getProductByProductNumber(String productNumber) {
        return Mapper.mapProductEntityToProductModel(productRepository
                .findByProductNumberAndHasItemOnStock(productNumber,0)
                .orElseThrow(() ->{
                    String message = String.format("Product with id: %s not found", productNumber);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }

    public void deleteProductById(Long id) {
        Optional<Product> deletedProduct = productRepository.findById(id);
        if (deletedProduct.isEmpty()) {
            String message = String.format("Product not exists with id: %s, cannot delete!", id);
            log.info(message);
            throw new NoSuchElementException(message);
        } else {
            productRepository.delete(deletedProduct.get());
        }
    }
}

