package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.dto.RegionNameResponseDto;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.region.RegionInternalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        String rate = "3%";
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

    @Test
    void 원하는_숫자에_맞는_값을가져오는지_테스트() {
        String name = "name";
        Region region = Region.createRegion(name);
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();

        when(regionSupInfoInternalService.findTopOf(anyInt())).thenReturn(Arrays.asList(regionSupportInformation));

        List<RegionNameResponseDto> actualResult = regionSupInfoService.findTopOf(1);
        assertThat(actualResult.size()).isEqualTo(1);
        assertThat(actualResult.get(0).getRegion()).isEqualTo(name);
    }

    @Test
    void 보전_비율_가장_작은_추천_기관명_테스트() {

        String name = "name";
        Region region = Region.createRegion(name);
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        when(regionSupInfoInternalService.findMinRateInfo()).thenReturn(regionSupportInformation);

        RegionNameResponseDto actualResult = regionSupInfoService.findMinRate();

        assertThat(actualResult.getRegion()).isEqualTo(name);
    }
}