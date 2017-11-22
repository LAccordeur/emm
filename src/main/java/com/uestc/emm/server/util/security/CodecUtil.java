package com.uestc.emm.server.util.security;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author : guoyang
 * @Description :加密等安全相关的工具类
 * @Date : Created on 2017/10/28
 */
public class CodecUtil {

    public static String encryptWithSHA256(String message) {
        MessageDigest messageDigest;
        String encodeString = "";

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(message.getBytes("UTF-8"));
            encodeString = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodeString;
    }
}
