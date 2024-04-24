package hu.webler.weblerstoragemanager.controller;

import hu.webler.weblerstoragemanager.model.*;
import hu.webler.weblerstoragemanager.service.SupplierOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SupplierOrderController {

    private final SupplierOrderService supplierOrderService;

    @PostMapping("/supplier-orders")
    public ResponseEntity<SupplierOrderModel> createSupplierOrder(@RequestBody SupplierOrderCreateModel createModel) {
        return ResponseEntity.status(201).body(supplierOrderService.createSupplierOrder(createModel));
    }

    @PutMapping("/supplier-orders/{id}")
    public ResponseEntity<SupplierOrderModel> updateSupplierOrder(@PathVariable Long id, @RequestBody SupplierOrderUpdateModel updateModel) {
        return ResponseEntity.status(200).body(supplierOrderService.updateSupplierOrder(id, updateModel));
    }

    @GetMapping("/supplier-orders")
    public ResponseEntity<List<SupplierOrderModel>> getAllSupplierOrder() {
        return ResponseEntity.status(200).body(supplierOrderService.getAllSupplierOrder());
    }

    @GetMapping("/supplier-orders/partner/{partnerId}")
    public ResponseEntity<List<SupplierOrderModel>> getAllByPartner(@PathVariable Long partnerId) {
        return ResponseEntity.status(200).body(supplierOrderService.getAllByPartner(partnerId));
    }

    @GetMapping("/supplier-orders/{id}")
    public ResponseEntity<SupplierOrderModel> getSupplierOrderById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(supplierOrderService.getSupplierOrderById(id));
    }

    @DeleteMapping("/supplier-orders/{id}")
    public ResponseEntity<Void> deleteSupplierOrder(@PathVariable Long id) {
        supplierOrderService.deleteSupplierOrderById(id);
        return ResponseEntity.status(204).body(null);
    }
}
