package com.ywl.study.springsecurity.security;

import lombok.extern.slf4j.Slf4j;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 密码认证工具类（MD5）
 *
 * @author zhubxa
 */
@Slf4j
public class MD5PasswordAuthenticator {
    protected static final String ALGORITHM = "MD5";

    /**
     * 验证密码
     *
     * @param password   明文
     * @param dbPassword 密文
     * @return boolean
     */
    public static boolean authentaication(String password, String dbPassword) {
        if (password == null || dbPassword == null)
            return false;
        String encodedPassword = createPassword(password);
        return Arrays.equals(encodedPassword.getBytes(), dbPassword.toUpperCase().getBytes());
    }

    public static String createPassword(String dataStr) {
        return createPassword(dataStr, "");
    }

    public static String createPassword(String dataStr, String salt) {
        try {
            dataStr = dataStr + salt;
            MessageDigest m = MessageDigest.getInstance(ALGORITHM);
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result.toUpperCase();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }
}
