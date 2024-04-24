package hu.webler.weblerstoragemanager.model;

import hu.webler.weblerstoragemanager.entity.SupplierOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerModel {

    private Long id;

    private String partnerName;

    private String partnerAddress;
}
