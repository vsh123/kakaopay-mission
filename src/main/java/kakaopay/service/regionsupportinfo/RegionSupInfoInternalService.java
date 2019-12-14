package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
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

    public RegionSupportInformation update(Region region, RegionSupInfoUpdateRequestDto updateRequestDto) {
        RegionSupportInformation regionSupportInformation = findByRegionCode(region.getCode());
        updateEntity(updateRequestDto, regionSupportInformation);
        return regionSupportInformation;
    }

    private void updateEntity(RegionSupInfoUpdateRequestDto updateRequestDto, RegionSupportInformation regionSupportInformation) {
        regionSupportInformation.updateTarget(updateRequestDto.getTarget());
        regionSupportInformation.updateUsage(updateRequestDto.getUsage());
        regionSupportInformation.updateLimitPay(updateRequestDto.getLimit());
        regionSupportInformation.updateRate(updateRequestDto.getRate());
        regionSupportInformation.updateInstitute(updateRequestDto.getInstitute());
        regionSupportInformation.updateMgmt(updateRequestDto.getMgmt());
        regionSupportInformation.updateReception(updateRequestDto.getReception());
    }
}
