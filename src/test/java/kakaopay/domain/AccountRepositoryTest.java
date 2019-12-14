package kakaopay.domain;

import kakaopay.exception.NotFoundAccountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void existByUserId() {
        assertThat(accountRepository.existsByUserId("userId")).isFalse();
    }

    @Test
    void findByUserId() {
        Account account = new Account("userId", "password");
        Account savedAccount = accountRepository.save(account);
        Account actualAccount = accountRepository.findByUserId(account.getUserId())
                .orElseThrow(NotFoundAccountException::new);
        assertThat(savedAccount).isEqualTo(actualAccount);
    }
}