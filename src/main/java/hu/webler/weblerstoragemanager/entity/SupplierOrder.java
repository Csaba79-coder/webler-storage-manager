package hu.webler.weblerstoragemanager.entity;

import hu.webler.weblerstoragemanager.entity.base.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierOrder extends Order {

    @CreationTimestamp
    private LocalDateTime arrivalDate = LocalDateTime.now();
}
