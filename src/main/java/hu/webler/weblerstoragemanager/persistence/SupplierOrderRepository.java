package hu.webler.weblerstoragemanager.persistence;

import hu.webler.weblerstoragemanager.entity.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
}
