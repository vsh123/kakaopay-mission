package kakaopay.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RegionRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;

    @Test
    void findByName() {
        String name = "name";
        Region region = Region.createRegion(name);

        Region savedRegion = regionRepository.save(region);

        Region findByNameRegion = regionRepository.findByName(name).orElseThrow(IllegalArgumentException::new);

        assertThat(savedRegion).isEqualTo(findByNameRegion);
    }
}