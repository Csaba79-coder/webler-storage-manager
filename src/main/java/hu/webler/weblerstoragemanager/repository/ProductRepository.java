package hu.webler.weblerstoragemanager.repository;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.value.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByQuantityGreaterThan(int quantity);

    Product findByIdAndQuantityGreaterThan(Long id, int quantity);

    Product findByProductNumberAndQuantityGreaterThan(String productNumber, int quantity);

    List<Product> findAllByRawMateril(Category ALAPANYAG);

    List<Product> findAllByPurchasedItem(Category VÁSÁROLT_TÉTEL);

    List<Product> findAllByManufacturedItem(Category GYÁRTOTT_TÉTEL);
}