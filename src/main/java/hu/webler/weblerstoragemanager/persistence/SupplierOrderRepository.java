package hu.webler.weblerstoragemanager.persistence;

import hu.webler.weblerstoragemanager.entity.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {

    Optional<SupplierOrder> findById(Long id);
}
