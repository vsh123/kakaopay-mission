package kakaopay.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionSupportInformationRepository extends JpaRepository<RegionSupportInformation, Long> {
    Optional<RegionSupportInformation> findByRegionCode(String regionCode);
}
