package kakaopay.service.regionsupportinfo;

import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.exception.NotFoundRegionSupportInformationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionSupInfoInternalService {
    private final RegionSupportInformationRepository regionSupportInformationRepository;

    public RegionSupInfoInternalService(RegionSupportInformationRepository regionSupportInformationRepository) {
        this.regionSupportInformationRepository = regionSupportInformationRepository;
    }

    @Transactional(readOnly = true)
    public RegionSupportInformation findByRegionCode(String code) {
        return regionSupportInformationRepository.findByRegionCode(code)
                .orElseThrow(NotFoundRegionSupportInformationException::new);
    }
}
