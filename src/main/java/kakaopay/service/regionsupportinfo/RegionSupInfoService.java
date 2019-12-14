package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.dto.*;
import kakaopay.service.region.RegionInternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<RegionNameResponseDto> findTopOf(int numberOfRegionSupInfos) {
        return regionSupInfoInternalService.findTopOf(numberOfRegionSupInfos).stream()
                .map(regionSupInfo -> RegionConverter.toRegionNameResponseDto(regionSupInfo.getRegion()))
                .collect(Collectors.toList());
    }

    public RegionNameResponseDto findMinRate() {
        return RegionConverter.toRegionNameResponseDto(regionSupInfoInternalService.findMinRateInfo().getRegion());
    }
}
