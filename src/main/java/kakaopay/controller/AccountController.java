package kakaopay.controller;

import kakaopay.dto.AccountDto;
import kakaopay.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> signUp(AccountDto accountDto) {
        return ResponseEntity.ok(accountService.signUp(accountDto));
    }
}
