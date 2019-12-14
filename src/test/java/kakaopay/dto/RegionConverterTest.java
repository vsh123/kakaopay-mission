package kakaopay.dto;

import kakaopay.domain.Region;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionConverterTest {
    @Test
    void toRegionNameResponseDto() {
        String name = "name";
        Region region = Region.createRegion(name);
        RegionNameResponseDto result = RegionConverter.toREgionNameResponseDto(region);

        assertThat(result.getRegion()).isEqualTo(name);
    }
}