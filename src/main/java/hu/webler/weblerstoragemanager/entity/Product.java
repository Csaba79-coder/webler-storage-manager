package hu.webler.weblerstoragemanager.entity;

import hu.webler.weblerstoragemanager.value.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Column(unique = true)
    private String productNumber;

    @Column(unique = true)
    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    private int quantity = 0;

    @ManyToMany(mappedBy = "products")
    private List<SupplierOrder> supplierOrders;
}
