package kakaopay.service.serach;

import kakaopay.domain.Region;
import kakaopay.domain.RegionRepository;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.exception.NotFoundRegionSupportInformationException;
import kakaopay.exception.NotSupportRegionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchInternalServiceTest {
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private RegionSupportInformationRepository regionSupportInformationRepository;
    @InjectMocks
    private SearchInternalService searchInternalService;

    @Test
    void 없는_지자체명을_입력했을_때_에러를_발생시킨다() {
        when(regionRepository.findByName(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(NotSupportRegionException.class, () -> searchInternalService.findByRegionName("name"));
    }

    @Test
    void 자자체가_지원하는_정보가_없을때__입력했을_때_에러를_발생시킨다() {
        Region region = Region.createRegion("name");

        when(regionRepository.findByName(any())).thenReturn(Optional.ofNullable(region));
        when(regionSupportInformationRepository.findByRegionCode(region.getCode())).thenReturn(Optional.ofNullable(null));

        assertThrows(NotFoundRegionSupportInformationException.class, () -> searchInternalService.findByRegionName("name"));
    }

    @Test
    void 지원_정보를_정상적으로_불러오는지_테스트() {
        Region region = Region.createRegion("name");
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        when(regionRepository.findByName(any())).thenReturn(Optional.ofNullable(region));
        when(regionSupportInformationRepository.findByRegionCode(region.getCode())).thenReturn(Optional.ofNullable(regionSupportInformation));

        RegionSupportInformation actualRegionSupportInformation = searchInternalService.findByRegionName("name");
        assertThat(actualRegionSupportInformation.getRegion().getCode()).isEqualTo(region.getCode());
    }
}