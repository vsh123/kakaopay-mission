package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.region.RegionInternalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionSupInfoServiceTest {
    @Mock
    private RegionInternalService regionInternalService;
    @Mock
    private RegionSupInfoInternalService regionSupInfoInternalService;
    @InjectMocks
    private RegionSupInfoService regionSupInfoService;

    @Test
    void updateTest() {
        String target = "target";
        String usage = "usage";
        String limitPay = "1억원 이내";
        String rate = "rate";
        String institute = "institute";
        String mgmt = "mgmt";
        String reception = "reception";

        Region region = Region.createRegion("name");

        RegionSupInfoUpdateRequestDto requestDto =
                new RegionSupInfoUpdateRequestDto(region.getName(), target, usage, limitPay, rate,
                        institute, mgmt, reception);

        RegionSupportInformation updatedRegionSupInfo = new RegionSupportInformation.Builder()
                .region(region)
                .target(target)
                .usage(usage)
                .limitPay(limitPay)
                .rate(rate)
                .institute(institute)
                .mgmt(mgmt)
                .reception(reception)
                .build();

        when(regionInternalService.findByName("name")).thenReturn(region);
        when(regionSupInfoInternalService.update(region, requestDto)).thenReturn(updatedRegionSupInfo);
        RegionSupportInfoResponseDto responseDto = regionSupInfoService.update(requestDto);

        assertThat(responseDto.getLimit()).isEqualTo(limitPay);
        assertThat(responseDto.getInstitute()).isEqualTo(institute);
    }
}