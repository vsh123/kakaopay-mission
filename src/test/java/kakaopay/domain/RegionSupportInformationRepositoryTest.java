package kakaopay.domain;

import kakaopay.exception.NotFoundRegionSupportInformationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RegionSupportInformationRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionSupportInformationRepository regionSupportInformationRepository;

    @Test
    void findByRegionCode() {
        String name = "name";
        Region region = Region.createRegion(name);
        Region savedRegion = regionRepository.save(region);
        RegionSupportInformation regionSupInfo = new RegionSupportInformation.Builder()
                .region(region)
                .build();
        regionSupportInformationRepository.save(regionSupInfo);

        RegionSupportInformation expectedRegionSupInfo =
                regionSupportInformationRepository.findByRegionCode(savedRegion.getCode())
                        .orElseThrow(IllegalArgumentException::new);

        assertThat(expectedRegionSupInfo.getRegion()).isEqualTo(savedRegion);
    }

    @Test
    void findOderByTest() {
        Pageable pageable = PageRequest.of(0, 1);
        List<RegionSupportInformation> result = regionSupportInformationRepository.findAll(pageable).getContent();

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void findMin() {
        RegionSupportInformation min = regionSupportInformationRepository.findFirstByOrderByRateAverageRateAsc()
                .orElseThrow(NotFoundRegionSupportInformationException::new);
        assertThat(min.getRate().getAverageRate()).isEqualTo(1.0);
    }
}