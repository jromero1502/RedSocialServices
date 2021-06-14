package com.jromero.redsocial.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class StringValidatorTest {
    
    @Test
    public void isEmailTest() {
        String email = "romerojulian115@gmail.com";
        boolean isEmail = StringValidator.isEmail(email);
        assertEquals(false, isEmail);
    }
}
