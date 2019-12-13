package kakaopay.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionTest {

    @Test
    void 빌더_패턴_테스트() {
        String code = "code";
        String name = "name";
        Region region = new Region.Builder()
                .code(code)
                .name(name)
                .build();

        assertThat(region.getCode()).isEqualTo(code);
        assertThat(region.getName()).isEqualTo(name);
    }

    @Test
    void createNewRegion() {
        String name = "name";

        Region region = Region.createRegion(name);

        assertThat(region.getCode()).isNotNull();
    }
}