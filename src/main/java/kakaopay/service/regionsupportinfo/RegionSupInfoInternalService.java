package kakaopay.service.regionsupportinfo;

import kakaopay.domain.Region;
import kakaopay.domain.RegionSupportInformation;
import kakaopay.domain.RegionSupportInformationRepository;
import kakaopay.dto.RegionSupInfoUpdateRequestDto;
import kakaopay.exception.NotFoundRegionSupportInformationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionSupInfoInternalService {
    private final RegionSupportInformationRepository regionSupportInformationRepository;

    public RegionSupInfoInternalService(RegionSupportInformationRepository regionSupportInformationRepository) {
        this.regionSupportInformationRepository = regionSupportInformationRepository;
    }

    @Transactional(readOnly = true)
    public RegionSupportInformation findByRegionCode(String code) {
        return regionSupportInformationRepository.findByRegionCode(code)
                .orElseThrow(NotFoundRegionSupportInformationException::new);
    }

    @Transactional(readOnly = true)
    public List<RegionSupportInformation> findTopOf(int numberOfRegionSupInfos) {
        Sort sort = new Sort(Sort.Direction.DESC, "limitPay_pay");
        sort = sort.and(new Sort(Sort.Direction.ASC, "rate_averageRate"));
        Pageable pageable = PageRequest.of(0, numberOfRegionSupInfos, sort);

        return regionSupportInformationRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public RegionSupportInformation findMinRateInfo() {
        return regionSupportInformationRepository.findFirstByOrderByRateAverageRateAsc()
                .orElseThrow(NotFoundRegionSupportInformationException::new);
    }

    public RegionSupportInformation update(Region region, RegionSupInfoUpdateRequestDto updateRequestDto) {
        RegionSupportInformation regionSupportInformation = findByRegionCode(region.getCode());
        updateEntity(updateRequestDto, regionSupportInformation);
        return regionSupportInformation;
    }

    private void updateEntity(RegionSupInfoUpdateRequestDto updateRequestDto, RegionSupportInformation regionSupportInformation) {
        regionSupportInformation.updateTarget(updateRequestDto.getTarget());
        regionSupportInformation.updateUsage(updateRequestDto.getUsage());
        regionSupportInformation.updateLimitPay(updateRequestDto.getLimit());
        regionSupportInformation.updateRate(updateRequestDto.getRate());
        regionSupportInformation.updateInstitute(updateRequestDto.getInstitute());
        regionSupportInformation.updateMgmt(updateRequestDto.getMgmt());
        regionSupportInformation.updateReception(updateRequestDto.getReception());
    }
}
