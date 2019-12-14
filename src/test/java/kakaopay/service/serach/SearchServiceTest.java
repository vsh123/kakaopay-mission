package kakaopay.service.serach;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.dto.RegionSupportInfoResponseDto;
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
    private SearchInternalService searchInternalService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void findByRegionName() {
        String name = "name";
        Region region = Region.createRegion(name);
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        when(searchInternalService.findByRegionName(name)).thenReturn(regionSupportInformation);

        RegionSupportInfoResponseDto actualRegionInfoResponseDto = searchService.findByRegionName(name);

        assertThat(actualRegionInfoResponseDto.getRegion()).isEqualTo(name);
    }
}