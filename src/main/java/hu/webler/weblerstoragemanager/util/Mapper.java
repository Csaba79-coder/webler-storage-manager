package hu.webler.weblerstoragemanager.util;

import hu.webler.weblerstoragemanager.entity.Partner;
import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.entity.SupplierOrder;
import hu.webler.weblerstoragemanager.model.*;

public class Mapper {

    public static ProductModel mapProductEntityToProductModel(Product entity){
        ProductModel model = new ProductModel();
        model.setId(entity.getId());
        model.setProductNumber(entity.getProductNumber());
        model.setProductName(entity.getProductName());
        model.setCategory(entity.getCategory());
        model.setDescription(entity.getDescription());
        model.setQuantity(entity.getQuantity());
        return model;
    }

    public static Product mapProductCreateModelToProductEntity(ProductCreateModel model){
        Product entity = new Product();
        entity.setProductNumber(model.getProductNumber());
        entity.setProductName(model.getProductName());
        entity.setCategory(model.getCategory());
        entity.setDescription(model.getDescription());
        return entity;
    }

    public static PartnerModel mapPartnerEntityToPartnerModel(Partner entity){
        PartnerModel model = new PartnerModel();
        model.setId(entity.getId());
        model.setPartnerName(entity.getPartnerName());
        model.setPartnerAddress(entity.getPartnerAddress());
        return model;
    }

    public static Partner mapPartnerCreateModelToPartnerEntity(PartnerCreateModel model){
        Partner entity = new Partner();
        entity.setPartnerName(model.getPartnerName());
        entity.setPartnerAddress(model.getPartnerAddress());
        return entity;
    }

    public static SupplierOrderModel mapSupplierOrderEntityToSupplierOrderModel(SupplierOrder entity){
        SupplierOrderModel model = new SupplierOrderModel();
        model.setId(entity.getId());
        model.setPartner(entity.getPartner());
        model.setArrivalDate(entity.getArrivalDate());
        model.setProducts(entity.getProducts());
        return model;
    }

    public static SupplierOrder mapSupplierOrderCreateModelToSupplierOrderEntity(SupplierOrderCreateModel model){
        SupplierOrder entity = new SupplierOrder();
        entity.setPartner(model.getPartner());
        entity.setProducts(model.getProducts());
        return entity;
    }
    private Mapper(){

    }
}
