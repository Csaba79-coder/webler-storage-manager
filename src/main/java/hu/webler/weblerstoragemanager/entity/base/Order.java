package hu.webler.weblerstoragemanager.entity.base;

import hu.webler.weblerstoragemanager.value.Category;
import hu.webler.weblerstoragemanager.value.OrderType;
import jakarta.persistence.*;
import lombok.Getter;

@MappedSuperclass
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
}
