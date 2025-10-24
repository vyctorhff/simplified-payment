package br.com.challenge.payment.core.model;

import br.com.challenge.payment.helpers.model.UserHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testShouldBeLojist() {
        User user = UserHelper.createUserLojist();
        assertTrue(user.isUserLojist());
    }

    @Test
    void testShouldUserCommon() {
        User user = UserHelper.createUserCommon();
        assertTrue(user.isUserCommon());
    }
}