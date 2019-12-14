package kakaopay.service.serach;

import kakaopay.dto.RegionSupportInfoConverter;
import kakaopay.dto.RegionSupportInfoResponseDto;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final SearchInternalService searchInternalService;

    public SearchService(SearchInternalService searchInternalService) {
        this.searchInternalService = searchInternalService;
    }

    public RegionSupportInfoResponseDto findByRegionName(String region) {
        return RegionSupportInfoConverter
                .toRegionSupportInfoResponseDto(searchInternalService.findByRegionName(region));
    }
}
