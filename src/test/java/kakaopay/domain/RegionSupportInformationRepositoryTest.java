package kakaopay.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}