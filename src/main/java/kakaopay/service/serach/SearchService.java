package kakaopay.service.serach;

import kakaopay.domain.Region;
import kakaopay.dto.RegionSupportInfoConverter;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.region.RegionInternalService;
import kakaopay.service.regionsupportinfo.RegionSupInfoInternalService;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final RegionInternalService regionInternalService;
    private final RegionSupInfoInternalService regionSupInfoInternalService;

    public SearchService(RegionInternalService regionInternalService, RegionSupInfoInternalService regionSupInfoInternalService) {
        this.regionInternalService = regionInternalService;
        this.regionSupInfoInternalService = regionSupInfoInternalService;
    }

    public RegionSupportInfoResponseDto findByRegionName(String regionName) {
        Region region = regionInternalService.findByName(regionName);
        return RegionSupportInfoConverter
                .toRegionSupportInfoResponseDto(regionSupInfoInternalService.findByRegionCode(region.getCode()));
    }
}
