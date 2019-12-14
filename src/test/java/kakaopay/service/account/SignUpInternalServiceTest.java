package kakaopay.service.account;

import kakaopay.domain.Account;
import kakaopay.domain.AccountRepository;
import kakaopay.dto.AccountDto;
import kakaopay.exception.AlreadyExistUserIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignUpInternalServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AccountInternalService accountInternalService;

    @Test
    void 정상적으로_회원가입_테스트() {
        String userId = "userId";
        String password = "password";
        AccountDto accountDto = new AccountDto(userId, password);

        when(passwordEncoder.encode(password)).thenReturn(password);
        when(accountRepository.existsByUserId(userId)).thenReturn(false);
        when(accountRepository.save(any())).thenReturn(new Account(userId, password));

        Account account = accountInternalService.signUp(accountDto);

        assertThat(account.getUserId()).isEqualTo(userId);
        assertThat(account.getPassword()).isEqualTo(password);
    }

    @Test
    void 이미존재하는_유저아이_회원가입_테스트() {
        String userId = "userId";
        String password = "password";
        AccountDto accountDto = new AccountDto(userId, password);

        when(passwordEncoder.encode(password)).thenReturn(password);
        when(accountRepository.existsByUserId(userId)).thenReturn(true);

        assertThrows(AlreadyExistUserIdException.class, () -> accountInternalService.signUp(accountDto));
    }
}