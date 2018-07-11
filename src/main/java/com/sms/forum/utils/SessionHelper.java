package com.sms.forum.utils;

import com.sms.forum.mem.MemcacheManager;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.UUID;

public class SessionHelper {

    /**
     * 生成session值
     **/
    public static String generateSession(String x, String ua) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            byte[] bytes = md5.digest((uuid + x + ua).getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * 验证session是否相同，且验证有效期
     **/
    public static boolean verifySession(String headerSession, String username) {
        if (!StringUtils.isEmpty(MemcacheManager.get().get("temp-" + username)) && MemcacheManager.get().get("temp-" + username).toString().equals(headerSession)) {
            return true;
        }
        return false;
    }

}
