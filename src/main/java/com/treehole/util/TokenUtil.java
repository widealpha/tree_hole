package com.treehole.util;

import java.util.Random;

public class TokenUtil {
    public static String getToken(int id) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append((char) (random.nextInt(25) + 'a'));
        }
        builder.append(id);
        for (int i = 0; i < 20; i++) {
            builder.append((char) (random.nextInt(25) + 'a'));
        }
        return builder.toString();
    }
}
