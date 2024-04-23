package hu.webler.weblerstoragemanager.service;

import hu.webler.weblerstoragemanager.entity.Partner;
import hu.webler.weblerstoragemanager.model.*;
import hu.webler.weblerstoragemanager.persistence.PartnerRepository;
import hu.webler.weblerstoragemanager.util.Mapper;
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
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public PartnerModel createPartner(PartnerCreateModel partnerCreateModel) {
        return Mapper.mapPartnerEntityToPartnerModel(partnerRepository
                .save(Mapper.mapPartnerCreateModelToPartnerEntity(partnerCreateModel)));
    }

    public PartnerModel updatePartner(Long id, PartnerUpdateModel updateModel) {
        Partner existingPartner = partnerRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Partner not exists with id: %s, cannot update", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
        String updatePartnerName = updateModel.getPartnerName();
        if (updatePartnerName != null) {
            existingPartner.setPartnerName(updatePartnerName);
        }
        String updatePartnerAddress = updateModel.getPartnerAddress();
        if (updatePartnerAddress != null) {
            existingPartner.setPartnerAddress(updatePartnerAddress);
        }
        return Mapper.mapPartnerEntityToPartnerModel(partnerRepository.save(existingPartner));
    }

    public List<PartnerModel> getAllPartner() {
        return partnerRepository.findAll()
                .stream()
                .map(Mapper::mapPartnerEntityToPartnerModel)
                .collect(Collectors.toList());
    }

    public List<PartnerModel> getAllByAddress(String partnerAddress) {
        return partnerRepository.findAll()
                .stream()
                .map(Mapper::mapPartnerEntityToPartnerModel)
                .filter(c -> c.getPartnerAddress().equals(partnerAddress))
                .collect(Collectors.toList());
    }

    public PartnerModel getPartnerById(Long id) {
        return Mapper.mapPartnerEntityToPartnerModel(partnerRepository.findById(id)
                .orElseThrow(() ->{
                    String message = String.format("Partner with id: %d not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }

    public PartnerModel getPartnerByPartnerName(String partnerName) {
        return Mapper.mapPartnerEntityToPartnerModel(partnerRepository
                .findByPartnerName(partnerName)
                .orElseThrow(() ->{
                    String message = String.format("Partner with partner name: %s not found", partnerName);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }

    public void deletePartnerById(Long id) {
        Optional<Partner> deletedPartner = partnerRepository.findById(id);
        if (deletedPartner.isEmpty()) {
            String message = String.format("Partner not exists with id: %s, cannot delete!", id);
            log.info(message);
            throw new NoSuchElementException(message);
        } else {
            partnerRepository.delete(deletedPartner.get());
        }
    }
}
