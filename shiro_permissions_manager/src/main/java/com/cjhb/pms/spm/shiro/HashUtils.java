package com.cjhb.pms.spm.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Hash Utils
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class HashUtils {

    public static String md5(String in) {
        return new Md5Hash(in).toString();
    }

    public static String sha1(String in) {
        return new Sha1Hash(in).toString();
    }

    public static String sha256(String in) {
        return new Sha256Hash(in).toString();
    }

    /**
     * 计算字符串HASH值
     * 
     * @param algorithmName 算法
     * @param in 字符串
     * @return
     */
    public static String hash(String algorithmName, String in) {
        return new SimpleHash(algorithmName, in).toString();
    }
}
