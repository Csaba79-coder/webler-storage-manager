package hu.webler.weblerstoragemanager.controller;

import hu.webler.weblerstoragemanager.model.*;
import hu.webler.weblerstoragemanager.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("/partners")
    public ResponseEntity<PartnerModel> createPartner(@RequestBody PartnerCreateModel createModel) {
        return ResponseEntity.status(201).body(partnerService.createPartner(createModel));
    }

    @PutMapping("/partners/{id}")
    public ResponseEntity<PartnerModel> updatePartner(@PathVariable Long id, @RequestBody PartnerUpdateModel updateModel) {
        return ResponseEntity.status(200).body(partnerService.updatePartner(id, updateModel));
    }

    @GetMapping("/partners")
    public ResponseEntity<List<PartnerModel>> getAllPartners() {
        return ResponseEntity.status(200).body(partnerService.getAllPartner());
    }

    @GetMapping("/partners/partnerAddress/{partnerAddress}")
    public ResponseEntity<List<PartnerModel>> getAllByAddress(@PathVariable String partnerAddress) {
        return ResponseEntity.status(200).body(partnerService.getAllByAddress(partnerAddress));
    }

    @GetMapping("/partners/{id}")
    public ResponseEntity<PartnerModel> getPartnerById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(partnerService.getPartnerById(id));
    }

    @GetMapping("/partners/by-partner-name/{partnerName}")
    public ResponseEntity<PartnerModel> getPartnerByPartnerName(@PathVariable String partnerName) {
        return ResponseEntity.status(200).body(partnerService.getPartnerByPartnerName(partnerName));
    }

    @DeleteMapping("/partners/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        partnerService.deletePartnerById(id);
        return ResponseEntity.status(204).body(null);
    }
}
