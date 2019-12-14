package kakaopay.dto;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionSupportInfoConverterTest {
    @Test
    void toRegionSupportInfoResponseDto() {
        String name = "name";
        String target = "target";
        String institute = "institute";
        String limitPay = "1억원 이내";
        String mgmt = "mgmt";
        String rate = "rate";
        String reception = "reception";
        String usage = "usage";

        Region region = Region.createRegion(name);

        RegionSupportInformation regionSupportInformation =
                new RegionSupportInformation.Builder()
                        .region(region)
                        .target(target)
                        .institute(institute)
                        .limitPay(limitPay)
                        .mgmt(mgmt)
                        .rate(rate)
                        .reception(reception)
                        .usage(usage)
                        .build();
        RegionSupportInfoResponseDto responseDto = RegionSupportInfoConverter.toRegionSupportInfoResponseDto(regionSupportInformation);

        assertThat(responseDto.getRegion()).isEqualTo(name);
        assertThat(responseDto.getLimit()).isEqualTo(limitPay);
    }
}