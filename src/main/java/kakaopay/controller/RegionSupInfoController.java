package kakaopay.controller;

import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.regionsupportinfo.RegionSupInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionSupInfoController {
    private final RegionSupInfoService regionSupInfoService;

    public RegionSupInfoController(RegionSupInfoService regionSupInfoService) {
        this.regionSupInfoService = regionSupInfoService;
    }

    @PutMapping("/api/regionsupinfos")
    public ResponseEntity<RegionSupportInfoResponseDto> update(RegionSupInfoUpdateRequestDto requestDto) {
        return ResponseEntity.ok(regionSupInfoService.update(requestDto));
    }
}
