package kakaopay.service.account;

import kakaopay.domain.Account;
import kakaopay.domain.AccountRepository;
import kakaopay.dto.AccountDto;
import kakaopay.exception.AlreadyExistUserIdException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountInternalService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountInternalService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account signUp(AccountDto accountDto) {
        String userId = accountDto.getUserId();
        String encryptedPassword = passwordEncoder.encode(accountDto.getPassword());

        if (accountRepository.existsByUserId(userId)) {
            throw new AlreadyExistUserIdException();
        }

        Account account = new Account(userId, encryptedPassword);

        return accountRepository.save(account);
    }
}
