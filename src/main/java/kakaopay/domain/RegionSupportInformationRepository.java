package kakaopay.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegionSupportInformationRepository extends JpaRepository<RegionSupportInformation, Long> {
    Optional<RegionSupportInformation> findByRegionCode(String regionCode);

    Page<RegionSupportInformation> findAll(Pageable pageable);

    Optional<RegionSupportInformation> findFirstByOrderByRateAverageRateAsc();
}
