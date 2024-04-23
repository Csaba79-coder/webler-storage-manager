package hu.webler.weblerstoragemanager.util;

import hu.webler.weblerstoragemanager.entity.Partner;
import hu.webler.weblerstoragemanager.model.PartnerCreateModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void mapPartnerCreateModelToPartnerEntity() {

        PartnerCreateModel model = new PartnerCreateModel();
        model.setPartnerName("TestPartner");
        model.setPartnerAddress("123 Test Address");

        Partner result = Mapper.mapPartnerCreateModelToPartnerEntity(model);

        assertEquals(model.getPartnerName(), result.getPartnerName());
        assertEquals(model.getPartnerAddress(), result.getPartnerAddress());
    }
}