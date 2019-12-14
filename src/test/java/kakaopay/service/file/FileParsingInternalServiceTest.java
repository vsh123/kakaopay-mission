package kakaopay.service.file;

import kakaopay.domain.Region;
import kakaopay.domain.RegionRepository;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileParsingInternalServiceTest {
    @Mock
    private RegionRepository regionRepository;

    @Mock
    private RegionSupportInformationRepository regionSupportInformationRepository;

    @InjectMocks
    private FileParsingInternalService fileParsingInternalService;

    @Test
    void parsingCsvFile() throws IOException {
        String name = "name";
        String target = "target";
        String institute = "institute";
        String limitPay = "1억원 이내";
        String mgmt = "mgmt";
        String rate = "rate";
        String reception = "reception";
        String usage = "usage";

        Region region = Region.createRegion(name);

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

        MultipartFile file = new MockMultipartFile("test.csv", new FileInputStream(new File("src/test/resources/test.csv")));

        when(regionRepository.findByName(any())).thenReturn(Optional.ofNullable(region));
        when(regionSupportInformationRepository.save(any())).thenReturn(regionSupportInformation);

        List<RegionSupportInformation> regionSupportInformations = fileParsingInternalService.parsingCsvFile(file);

        assertThat(regionSupportInformations.contains(regionSupportInformation)).isTrue();
    }
}