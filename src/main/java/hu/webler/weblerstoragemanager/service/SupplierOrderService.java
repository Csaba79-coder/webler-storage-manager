package hu.webler.weblerstoragemanager.service;

import hu.webler.weblerstoragemanager.entity.Partner;
import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.entity.SupplierOrder;
import hu.webler.weblerstoragemanager.model.*;
import hu.webler.weblerstoragemanager.persistence.SupplierOrderRepository;
import hu.webler.weblerstoragemanager.util.Mapper;
import hu.webler.weblerstoragemanager.util.StockUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierOrderService {

    private final SupplierOrderRepository supplierOrderRepository;
    StockUpdater stockUpdater = new StockUpdater();
    public SupplierOrderModel createSupplierOrder(SupplierOrderCreateModel supplierOrderCreateModel) {
        List<Product> productList = supplierOrderCreateModel.getProducts();
        stockUpdater.stockIncrease(productList);
        return Mapper.mapSupplierOrderEntityToSupplierOrderModel(supplierOrderRepository
                .save(Mapper.mapSupplierOrderCreateModelToSupplierOrderEntity(supplierOrderCreateModel)));
    }

    public SupplierOrderModel updateSupplierOrder(Long id, SupplierOrderUpdateModel updateModel) {
        SupplierOrder existingSupplierOrder = supplierOrderRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Supplier order not exists with id: %s, cannot update", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
        Partner updatePartner = updateModel.getPartner();
        if (updatePartner != null) {
            existingSupplierOrder.setPartner(updatePartner);
        }
        List<Product> productList = existingSupplierOrder.getProducts();
        List<Product> updateProductList = updateModel.getProducts();
        if (updateProductList!= null) {
            stockUpdater.stockDecrease(productList);
            stockUpdater.stockIncrease(updateProductList);
            existingSupplierOrder.setProducts(updateProductList);
        }
        return Mapper.mapSupplierOrderEntityToSupplierOrderModel(supplierOrderRepository.save(existingSupplierOrder));
    }

    public List<SupplierOrderModel> getAllSupplierOrder() {
        return supplierOrderRepository.findAll()
                .stream()
                .map(Mapper::mapSupplierOrderEntityToSupplierOrderModel)
                .collect(Collectors.toList());
    }

    public SupplierOrderModel getSupplierOrderById(Long id) {
        return Mapper.mapSupplierOrderEntityToSupplierOrderModel(supplierOrderRepository.findById(id)
                .orElseThrow(() ->{
                    String message = String.format("Supplier order with id: %d not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }
    public List<SupplierOrderModel> getAllByPartner(Long partnerId) {
        return supplierOrderRepository.findAll()
                .stream()
                .map(Mapper::mapSupplierOrderEntityToSupplierOrderModel)
                .filter(c -> c.getPartner().getId().equals(partnerId))
                .collect(Collectors.toList());
    }

    public void deleteSupplierOrderById(Long id) {
        Optional<SupplierOrder> deletedSupplierOrder = supplierOrderRepository.findById(id);
        if (deletedSupplierOrder.isEmpty()) {
            String message = String.format("Supplier order not exists with id: %s, cannot delete!", id);
            log.info(message);
            throw new NoSuchElementException(message);
        } else {
            List<Product> productList = deletedSupplierOrder.get().getProducts();
            stockUpdater.stockDecrease(productList);
            supplierOrderRepository.delete(deletedSupplierOrder.get());
        }
    }
}
