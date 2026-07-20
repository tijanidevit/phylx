package com.tijanidevit.phylx.common.util;

import java.security.SecureRandom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String numericCode(int length) {
        int bound = (int) Math.pow(10, length);
        return String.format("%0" + length + "d", RANDOM.nextInt(bound));
    }
}
