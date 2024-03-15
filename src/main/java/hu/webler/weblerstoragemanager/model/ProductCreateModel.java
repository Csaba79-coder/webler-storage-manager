package hu.webler.weblerstoragemanager.model;

import hu.webler.weblerstoragemanager.value.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateModel {

    private String productNumber;

    private String productName;

    private Category category;

    private String description;
}
