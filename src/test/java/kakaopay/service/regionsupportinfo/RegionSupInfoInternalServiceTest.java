package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.exception.NotFoundRegionSupportInformationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionSupInfoInternalServiceTest {
    @Mock
    private RegionSupportInformationRepository regionSupportInformationRepository;
    @InjectMocks
    private RegionSupInfoInternalService regionSupInfoInternalService;

    @Test
    void findAll() {
        Region region = Region.createRegion("name");
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        when(regionSupportInformationRepository.findAll()).thenReturn(Arrays.asList(regionSupportInformation));

        List<RegionSupportInformation> result = regionSupInfoInternalService.findAll();
        assertThat(result.size()).isEqualTo(1);
    }

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

    @Test
    void updateTest() {
        Region region = Region.createRegion("name");
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        RegionSupInfoUpdateRequestDto requestDto =
                new RegionSupInfoUpdateRequestDto(region.getName(), "target", "usage", "1억원 이내", "3%",
                        "institute", "mgmt", "reception");

        when(regionSupportInformationRepository.findByRegionCode(region.getCode())).thenReturn(Optional.ofNullable(regionSupportInformation));

        RegionSupportInformation updateRegionSupInfo = regionSupInfoInternalService.update(region, requestDto);
        assertThat(updateRegionSupInfo.getTarget()).isEqualTo("target");
        assertThat(updateRegionSupInfo.getReception()).isEqualTo("reception");
    }

    @Test
    void findTopOf() {
        Region region = Region.createRegion("name");
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .region(region)
                .build();

        when(regionSupportInformationRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(regionSupportInformation)));

        List<RegionSupportInformation> actualResult = regionSupInfoInternalService.findTopOf(1);
        assertThat(actualResult.size()).isEqualTo(1);
    }

    @Test
    void findFirstOrderByMinRate() {
        RegionSupportInformation regionSupportInformation = new RegionSupportInformation.Builder()
                .rate("1%")
                .build();
        when(regionSupportInformationRepository.findFirstByOrderByRateAverageRateAsc()).thenReturn(Optional.ofNullable(regionSupportInformation));

        RegionSupportInformation minRateInfo = regionSupInfoInternalService.findMinRateInfo();

        assertThat(minRateInfo.getRate().getAverageRate()).isEqualTo(1.0);
    }
}