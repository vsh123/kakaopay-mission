package kakaopay.controller;

import kakaopay.dto.RegionSupportInfoResponseDto;
import kakaopay.service.serach.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/regionsupinfos/{region}")
    public ResponseEntity<RegionSupportInfoResponseDto> findByRegionName(@PathVariable String region) {
        return ResponseEntity.ok(searchService.findByRegionName(region));
    }
}
