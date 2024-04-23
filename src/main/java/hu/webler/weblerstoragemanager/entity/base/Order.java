package hu.webler.weblerstoragemanager.entity.base;

import hu.webler.weblerstoragemanager.entity.Partner;
import hu.webler.weblerstoragemanager.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Identifier{

    @ManyToMany
    @JoinTable(
            name = "supplier_order_product",
            joinColumns = @JoinColumn(name = "supplier_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
}
