package kakaopay.controller;

import kakaopay.dto.RegionNameResponseDto;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.regionsupportinfo.RegionSupInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionSupInfoController {
    private final RegionSupInfoService regionSupInfoService;

    public RegionSupInfoController(RegionSupInfoService regionSupInfoService) {
        this.regionSupInfoService = regionSupInfoService;
    }

    @GetMapping("/api/regionsupinfos")
    public ResponseEntity<List<RegionNameResponseDto>> findTopOf(@RequestParam("k") int numberOfRegionSupInfos) {
        return ResponseEntity.ok(regionSupInfoService.findTopOf(numberOfRegionSupInfos));
    }

    @GetMapping("/api/regionsupinfos/min")
    public ResponseEntity<RegionNameResponseDto> findMinRate() {
        return ResponseEntity.ok(regionSupInfoService.findMinRate());
    }

    @PutMapping("/api/regionsupinfos")
    public ResponseEntity<RegionSupportInfoResponseDto> update(RegionSupInfoUpdateRequestDto requestDto) {
        return ResponseEntity.ok(regionSupInfoService.update(requestDto));
    }
}
