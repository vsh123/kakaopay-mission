package kakaopay.service.serach;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.region.RegionInternalService;
import kakaopay.service.regionsupportinfo.RegionSupInfoInternalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    @Mock
    private RegionInternalService regionInternalService;
    @Mock
    private RegionSupInfoInternalService regionSupInfoInternalService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void findByRegionName() {
        String name = "name";
        String limitPay = "추천금액 이내";
        Region region = Region.createRegion(name);
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .limitPay(limitPay)
                .build();
        when(regionInternalService.findByName(name)).thenReturn(region);
        when(regionSupInfoInternalService.findByRegionCode(region.getCode())).thenReturn(regionSupportInformation);

        RegionSupportInfoResponseDto actualRegionInfoResponseDto = searchService.findByRegionName(name);

        assertThat(actualRegionInfoResponseDto.getRegion()).isEqualTo(name);
    }
}