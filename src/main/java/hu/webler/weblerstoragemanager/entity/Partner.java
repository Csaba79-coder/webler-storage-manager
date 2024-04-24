package hu.webler.weblerstoragemanager.entity;

import hu.webler.weblerstoragemanager.entity.base.Identifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partner extends Identifier {

    @Column(unique = true)
    private String partnerName;

    private String partnerAddress;

    @OneToMany(mappedBy = "partner")
    private List<SupplierOrder> supplierOrders;
}
