package kakaopay.service.account;

import kakaopay.dto.AccountDto;
import kakaopay.utils.JwtFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountInternalService accountInternalService;
    private final JwtFactory jwtFactory;

    public AccountService(AccountInternalService accountInternalService, JwtFactory jwtFactory) {
        this.accountInternalService = accountInternalService;
        this.jwtFactory = jwtFactory;
    }

    public String signUp(AccountDto accountDto) {
        return jwtFactory.createToken(accountInternalService.signUp(accountDto));
    }

    public String login(AccountDto accountDto) {
        return jwtFactory.createToken(accountInternalService.login(accountDto));
    }
}
