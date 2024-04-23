package hu.webler.weblerstoragemanager.model;

import hu.webler.weblerstoragemanager.entity.Partner;
import hu.webler.weblerstoragemanager.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierOrderModel {

    private Long id;

    private LocalDateTime arrivalDate;

    private Partner partner;

    private List<Product> products;
}
