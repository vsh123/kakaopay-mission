package kakaopay.service.serach;

import kakaopay.domain.Region;
import kakaopay.domain.RegionRepository;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.exception.NotFoundRegionSupportInformationException;
import kakaopay.exception.NotSupportRegionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchInternalService {
    private final RegionRepository regionRepository;
    private final RegionSupportInformationRepository regionSupportInformationRepository;

    public SearchInternalService(RegionRepository regionRepository, RegionSupportInformationRepository regionSupportInformationRepository) {
        this.regionRepository = regionRepository;
        this.regionSupportInformationRepository = regionSupportInformationRepository;
    }

    @Transactional(readOnly = true)
    public RegionSupportInformation findByRegionName(String regionName) {
        Region region = regionRepository.findByName(regionName).orElseThrow(NotSupportRegionException::new);
        return regionSupportInformationRepository.findByRegionCode(region.getCode())
                .orElseThrow(NotFoundRegionSupportInformationException::new);
    }
}
