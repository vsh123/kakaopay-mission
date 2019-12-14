package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.exception.NotFoundRegionSupportInformationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionSupInfoInternalServiceTest {
    @Mock
    private RegionSupportInformationRepository regionSupportInformationRepository;
    @InjectMocks
    private RegionSupInfoInternalService regionSupInfoInternalService;

    @Test
    void 자자체가_지원하는_정보가_없을때__입력했을_때_에러를_발생시킨다() {
        when(regionSupportInformationRepository.findByRegionCode("code")).thenReturn(Optional.ofNullable(null));

        assertThrows(NotFoundRegionSupportInformationException.class, () -> regionSupInfoInternalService.findByRegionCode("code"));
    }

    @Test
    void 지원_정보를_정상적으로_불러오는지_테스트() {
        Region region = Region.createRegion("name");
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        when(regionSupportInformationRepository.findByRegionCode(region.getCode())).thenReturn(Optional.ofNullable(regionSupportInformation));

        RegionSupportInformation actualRegionSupportInformation = regionSupInfoInternalService.findByRegionCode(region.getCode());
        assertThat(actualRegionSupportInformation.getRegion().getCode()).isEqualTo(region.getCode());
    }
}