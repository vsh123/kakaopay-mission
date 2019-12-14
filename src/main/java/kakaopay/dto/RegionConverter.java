package kakaopay.dto;

import kakaopay.domain.Region;

public class RegionConverter {
    public static RegionNameResponseDto toREgionNameResponseDto(Region region) {
        return new RegionNameResponseDto(region.getName());
    }
}
