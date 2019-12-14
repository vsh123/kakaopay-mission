package kakaopay.service.account;

import kakaopay.domain.Account;
import kakaopay.domain.AccountRepository;
import kakaopay.dto.AccountDto;
import kakaopay.exception.AlreadyExistUserIdException;
import kakaopay.exception.IllegalPasswordException;
import kakaopay.exception.NotFoundAccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountInternalServiceTest {
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

    @Test
    void loginTest() {
        String userId = "userId";
        String password = "password";
        Account account = new Account(userId, password);

        AccountDto accountDto = new AccountDto(userId, password);
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(accountRepository.findByUserId(userId)).thenReturn(Optional.ofNullable(account));

        Account actualAccount = accountInternalService.login(accountDto);

        assertThat(actualAccount.getUserId()).isEqualTo(account.getUserId());
    }

    @Test
    void 존재하지_않은_유저로_로그인() {
        String userId = "userId";
        String password = "password";

        AccountDto accountDto = new AccountDto(userId, password);
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(accountRepository.findByUserId(userId)).thenReturn(Optional.ofNullable(null));

        assertThrows(NotFoundAccountException.class, () -> accountInternalService.login(accountDto));
    }

    @Test
    void 틀린_비밀번호로_로그인() {
        String userId = "userId";
        String password = "password";
        String incorrectPassword = "1234";
        Account account = new Account(userId, password);

        AccountDto accountDto = new AccountDto(userId, incorrectPassword);
        when(passwordEncoder.encode(incorrectPassword)).thenReturn(incorrectPassword);
        when(accountRepository.findByUserId(userId)).thenReturn(Optional.ofNullable(account));

        assertThrows(IllegalPasswordException.class, () -> accountInternalService.login(accountDto));
    }
}