package br.com.challenge.payment.core.validation;

import br.com.challenge.payment.core.exception.FindUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FindUserValidationTest {

    private FindUserValidation validation;

    @BeforeEach
    void setUp() {
        this.validation = new FindUserValidation();
    }

    @Test
    void testShouldValidIdSuccessfully() {
        this.validation.findByIdValidation(2);
    }

    @ParameterizedTest
    @MethodSource("sourceTestShouldValidIdAsError")
    void testShouldValidIdAsError(Integer id) {
        assertThrows(FindUserException.class, () -> {
            this.validation.findByIdValidation(id);
        });
    }

    private static Stream<Arguments> sourceTestShouldValidIdAsError() {
        Integer nullalbe = null;
        return Stream.of(
                Arguments.of(nullalbe),
                Arguments.of(-1)
        );
    }

}