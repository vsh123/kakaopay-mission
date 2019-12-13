package kakaopay.dto;

import kakaopay.domain.RegionSupportInformation;
import org.modelmapper.ModelMapper;

public class RegionSupportInfoConverter {
    public static RegionSupportInfoResponseDto toRegionSupportInfoResponseDto(RegionSupportInformation regionSupportInformation) {
        RegionSupportInfoResponseDto responseDto = new ModelMapper().map(regionSupportInformation, RegionSupportInfoResponseDto.class);
        responseDto.setLimit(regionSupportInformation.getLimitPay());
        responseDto.setRegion(regionSupportInformation.getRegion().getName());
        return responseDto;
    }
}
