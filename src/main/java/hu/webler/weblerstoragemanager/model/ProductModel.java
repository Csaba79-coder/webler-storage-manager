package hu.webler.weblerstoragemanager.model;

import hu.webler.weblerstoragemanager.entity.SupplierOrder;
import hu.webler.weblerstoragemanager.value.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private Long id;

    private String productNumber;

    private String productName;

    private Category category;

    private String description;

    private int quantity = 0;
}
