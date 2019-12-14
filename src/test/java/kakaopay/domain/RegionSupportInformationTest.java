package kakaopay.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionSupportInformationTest {
    @Test
    void 빌더_패턴_테스트() {
        String code = "code";
        String name = "name";
        String target = "target";
        String institute = "institute";
        String limitPay = "limitPay";
        String mgmt = "mgmt";
        String rate = "rate";
        String reception = "reception";
        String usage = "usage";

        Region region = new Region.Builder()
                .code(code)
                .name(name)
                .build();

        RegionSupportInformation regionSupportInformation =
                new RegionSupportInformation.Builder()
                        .region(region)
                        .target(target)
                        .institute(institute)
                        .limitPay(limitPay)
                        .mgmt(mgmt)
                        .rate(rate)
                        .reception(reception)
                        .usage(usage)
                        .build();

        assertThat(regionSupportInformation.getMgmt()).isEqualTo(mgmt);
        assertThat(regionSupportInformation.getInstitute()).isEqualTo(institute);
        assertThat(regionSupportInformation.getLimitPay()).isEqualTo(limitPay);
        assertThat(regionSupportInformation.getRate()).isEqualTo(rate);
        assertThat(regionSupportInformation.getReception()).isEqualTo(reception);
        assertThat(regionSupportInformation.getTarget()).isEqualTo(target);
        assertThat(regionSupportInformation.getUsage()).isEqualTo(usage);
    }

    @Test
    void updateTest() {
        String code = "code";
        String name = "name";
        String target = "target";
        String institute = "institute";
        String limitPay = "limitPay";
        String mgmt = "mgmt";
        String rate = "rate";
        String reception = "reception";
        String usage = "usage";

        Region region = new Region.Builder()
                .code(code)
                .name(name)
                .build();

        RegionSupportInformation regionSupportInformation =
                new RegionSupportInformation.Builder()
                        .region(region)
                        .target(target)
                        .institute(institute)
                        .limitPay(limitPay)
                        .mgmt(mgmt)
                        .rate(rate)
                        .reception(reception)
                        .usage(usage)
                        .build();

        regionSupportInformation.updateTarget("");
        regionSupportInformation.updateInstitute("updateInstitute");

        assertThat(regionSupportInformation.getTarget()).isEqualTo(target);
        assertThat(regionSupportInformation.getInstitute()).isEqualTo("updateInstitute");
    }
}