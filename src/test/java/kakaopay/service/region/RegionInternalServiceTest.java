package kakaopay.service.region;

import kakaopay.domain.RegionRepository;
import kakaopay.exception.NotSupportRegionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionInternalServiceTest {
    @Mock
    private RegionRepository regionRepository;
    @InjectMocks
    private RegionInternalService regionInternalService;

    @Test
    void 없는_지자체명을_입력했을_때_에러를_발생시킨다() {
        when(regionRepository.findByName(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(NotSupportRegionException.class, () -> regionInternalService.findByName("name"));
    }
}