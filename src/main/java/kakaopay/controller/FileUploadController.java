package kakaopay.controller;

import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.file.FileParsingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileUploadController {
    private final FileParsingService fileParsingService;

    public FileUploadController(FileParsingService fileParsingService) {
        this.fileParsingService = fileParsingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<RegionSupportInfoResponseDto>> fileUpload(MultipartFile file) {
        return ResponseEntity.ok(fileParsingService.parsingCsvFile(file));
    }
}
