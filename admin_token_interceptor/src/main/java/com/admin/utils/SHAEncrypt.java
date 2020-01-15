package com.admin.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA 密码加密
 */
public class SHAEncrypt {
    /**
     * 直接获取加密后的字符串
     * @param password 传入的密码
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException
     */
    public static String getSHAStr(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return new BigInteger(md.digest()).toString(32);
    }
}
