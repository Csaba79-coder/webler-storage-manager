package hu.webler.weblerstoragemanager.entity.base;

import hu.webler.weblerstoragemanager.value.OrderType;
import jakarta.persistence.*;
import lombok.Getter;

@MappedSuperclass
@Getter
public class Order extends Identifier{

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
}
