package hu.webler.weblerstoragemanager.persistence;

import hu.webler.weblerstoragemanager.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findById(Long id);

    Optional<Partner> findByPartnerName(String partnerName);
}
