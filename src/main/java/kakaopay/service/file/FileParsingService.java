package kakaopay.service.file;

import kakaopay.dto.RegionSupportInfoConverter;
import kakaopay.dto.RegionSupportInfoResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileParsingService {
    private final FileParsingInternalService fileParsingInternalService;

    public FileParsingService(FileParsingInternalService fileParsingInternalService) {
        this.fileParsingInternalService = fileParsingInternalService;
    }

    public List<RegionSupportInfoResponseDto> parsingCsvFile(MultipartFile file) {
        return fileParsingInternalService.parsingCsvFile(file).stream()
                .map(RegionSupportInfoConverter::toRegionSupportInfoResponseDto)
                .collect(Collectors.toList());
    }
}
