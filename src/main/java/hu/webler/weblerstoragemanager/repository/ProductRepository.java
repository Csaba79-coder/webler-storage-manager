package hu.webler.weblerstoragemanager.repository;

import hu.webler.weblerstoragemanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional <Product> findByIdAndQuantityGreaterThan(Long id, int quantity);

    Optional <Product> findByProductNumberAndQuantityGreaterThan(String productNumber, int quantity);
}