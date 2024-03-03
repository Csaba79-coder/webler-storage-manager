package hu.webler.weblerstoragemanager.entity;

import hu.webler.weblerstoragemanager.enumeratio.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productNumber;

    @Column(unique = true)
    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    private int quantity;

    public Product(String productNumber, String productName, Category category, String description, int i) {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductCreateModel {
        private String productNumber;
        private String productName;
        private Category category;
        private String description;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductUpdateModel {
        private String productName;
        private String description;
    }
    
}


