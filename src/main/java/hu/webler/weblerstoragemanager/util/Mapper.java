package hu.webler.weblerstoragemanager.util;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.model.ProductCreateModel;
import hu.webler.weblerstoragemanager.model.ProductModel;

public class Mapper {

    public static ProductModel mapProductEntityToProductModel(Product entity) {
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

    private Mapper(){

    }
}
