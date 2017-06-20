package com.github.trang.typehandlers.test;

import com.github.trang.typehandlers.crypt.Crypt;

/**
 * @author trang
 */
public class TestCrypt implements Crypt {
    @Override
    public String encrypt(String content) {
        return "encrypt";
    }

    @Override
    public String encrypt(String content, String password) {
        return "encrypt";
    }

    @Override
    public String decrypt(String content) {
        return "decrypt";
    }

    @Override
    public String decrypt(String content, String password) {
        return "decrypt";
    }
}
