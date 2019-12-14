package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.dto.RegionSupportInfoConverter;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.region.RegionInternalService;
import org.springframework.stereotype.Service;

@Service
public class RegionSupInfoService {
    private final RegionInternalService regionInternalService;
    private final RegionSupInfoInternalService regionSupInfoInternalService;

    public RegionSupInfoService(RegionInternalService regionInternalService, RegionSupInfoInternalService regionSupInfoInternalService) {
        this.regionInternalService = regionInternalService;
        this.regionSupInfoInternalService = regionSupInfoInternalService;
    }

    public RegionSupportInfoResponseDto update(RegionSupInfoUpdateRequestDto updateRequestDto) {
        Region region = regionInternalService.findByName(updateRequestDto.getRegion());
        return RegionSupportInfoConverter
                .toRegionSupportInfoResponseDto(regionSupInfoInternalService.update(region, updateRequestDto));
    }
}
