package kakaopay.service.region;

import kakaopay.domain.Region;
import kakaopay.domain.RegionRepository;
import kakaopay.exception.NotSupportRegionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionInternalService {
    private final RegionRepository regionRepository;

    public RegionInternalService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Transactional(readOnly = true)
    public Region findByName(String region) {
        return regionRepository.findByName(region).orElseThrow(NotSupportRegionException::new);
    }
}
