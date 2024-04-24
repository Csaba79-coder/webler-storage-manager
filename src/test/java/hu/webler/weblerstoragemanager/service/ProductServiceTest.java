package hu.webler.weblerstoragemanager.service;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.model.ProductCreateModel;
import hu.webler.weblerstoragemanager.model.ProductModel;
import hu.webler.weblerstoragemanager.model.ProductUpdateModel;
import hu.webler.weblerstoragemanager.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct() {

        ProductCreateModel productCreateModel = new ProductCreateModel();
        Product productEntity = new Product();
        when(productRepository.save(any())).thenReturn(productEntity);

        ProductModel result = productService.createProduct(productCreateModel);

        assertNotNull(result);
    }

    @Test
    void updateProduct() {
        Long id = 1L;
        ProductUpdateModel updateModel = new ProductUpdateModel();
        Product productEntity = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any())).thenReturn(productEntity);

        ProductModel result = productService.updateProduct(id, updateModel);

        assertNotNull(result);
    }
}