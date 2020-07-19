package com.java.apps.algorithm.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AES(对称性)加密解密
 */
public class AES {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 默认加密/解密算法
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {
        String content = "1234567890";
        // DES加密后的数据
        String encryptStr = encrypt(content, "123321");
        System.out.println("加密后的密码：" + encryptStr);
        // DES解密后的数据
        String decryptStr = decrypt(encryptStr, "123321");
        // 还原后的密码
        System.out.println("解密后的密码：" + decryptStr);
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) throws Exception {
        // 待加密数据字节数组
        byte[] byteContent = content.getBytes(CHARSET);
        // 创建密匙KEY
        Key ky = generateKey(key);
        // 实例化密码器
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, ky);
        // 加密
        byte[] result = cipher.doFinal(byteContent);
        // base64再次加密（为了消除乱码）
        return new String(Base64.getEncoder().encode(result), CHARSET);
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) throws Exception {
        // 待加密数据字节数组
        byte[] byteContent = content.getBytes(CHARSET);
        // 创建密匙KEY
        Key ky = generateKey(key);
        // 实例化密码器
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, ky);
        // AES解密前先base64解密
        return new String(cipher.doFinal(Base64.getDecoder().decode(byteContent)), CHARSET);
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec generateKey(final String key) throws Exception {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        //AES 要求密钥长度为128
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes(CHARSET));
        keyGenerator.init(128, random);

        //生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //转换为AES专用密钥
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }
}
