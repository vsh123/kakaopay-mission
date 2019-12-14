package kakaopay.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void isCorrectPassword() {
        String userId = "userId";
        String password = "password";
        Account account = new Account(userId, password);

        assertThat(account.isCorrectPassword(password)).isTrue();
    }
}