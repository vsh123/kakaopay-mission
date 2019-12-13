package kakaopay.service.file;

import au.com.bytecode.opencsv.CSVReader;
import kakaopay.domain.Region;
import kakaopay.domain.RegionRepository;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.exception.CsvParsingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FileParsingInternalService {
    private static final int REGION_NAME_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int USAGE_INDEX = 3;
    private static final int LIMIT_PAY_INDEX = 4;
    private static final int RATE_INDEX = 5;
    private static final int INSTITUTE_INDEX = 6;
    private static final int MGMT_INDEX = 7;
    private static final int RECEPTION_INDEX = 8;

    private final RegionRepository regionRepository;
    private final RegionSupportInformationRepository regionSupportInformationRepository;

    public FileParsingInternalService(RegionRepository regionRepository, RegionSupportInformationRepository regionSupportInformationRepository) {
        this.regionRepository = regionRepository;
        this.regionSupportInformationRepository = regionSupportInformationRepository;
    }

    public List<RegionSupportInformation> parsingCsvFile(MultipartFile file) {
        List<RegionSupportInformation> datas = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            String[] row;
            //첫줄 제외
            csvReader.readNext();
            while ((row = csvReader.readNext()) != null) {
                String regionName = row[REGION_NAME_INDEX];
                Region region = getRegion(regionName);

                datas.add(getRegionSupportInformation(row, region));
            }
        } catch (IOException e) {
            throw new CsvParsingException("csv read에 실패하였습니다.");
        }
        return datas;
    }

    private Region getRegion(String regionName) {
        return regionRepository.findByName(regionName)
                .orElse(regionRepository.save(Region.createRegion(regionName)));
    }

    private RegionSupportInformation getRegionSupportInformation(String[] row, Region region) {
        return regionSupportInformationRepository.save(new RegionSupportInformation
                .Builder()
                .region(region)
                .target(row[TARGET_INDEX])
                .usage(row[USAGE_INDEX])
                .limitPay(row[LIMIT_PAY_INDEX])
                .rate(row[RATE_INDEX])
                .institute(row[INSTITUTE_INDEX])
                .mgmt(row[MGMT_INDEX])
                .reception(row[RECEPTION_INDEX])
                .build());
    }
}
