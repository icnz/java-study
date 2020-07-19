package com.java.apps.algorithm.encryption;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * DES(对称性)加密解密
 * 经验证使用initKeyWithPadding方式生产密钥时，和网站：http://tool.chacuo.net/crypt3des，结果相同
 */
public class DES {
    /**
     * 偏移变量，固定占8位字节
     */
    public static final String IV_PARAMETER = "01325896";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 默认加密/解密算法
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * 默认编码
     */
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {
        // 原始密码
        String password = "1234567890";
        // DES加密后的数据
        String encryptStr = encrypt(password, initKey("123321"));
        System.out.println("加密后的密码：" + encryptStr);
        // DES解密后的数据
        String decryptStr = decrypt(encryptStr, initKey("123321"));
        // 还原后的密码
        System.out.println("解密后的密码：" + decryptStr);

        // String srcFile = "E:\\Local\\Desktop\\test.txt";
        // String distFile = "E:\\Local\\Desktop\\test.db";
        // String distFile2 = "E:\\Local\\Desktop\\test1.txt";
        // encryptFile(srcFile, distFile, initKey("123321"));
        // decryptFile(distFile, distFile2, initKey("123321"));
    }

    /**
     * DES加密数据
     *
     * @param content 待加密数据
     * @param key     加密KEY
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception {
        // 待加密数据字节数组
        byte[] byteContent = content.getBytes(CHARSET);
        // 创建密匙KEY
        Key k = generateKey(key);
        // 实例化密码器
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 偏移变量
        IvParameterSpec ips = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, k, ips);
        // 加密后数据字节数组
        byte[] encryptBytes = cipher.doFinal(byteContent);
        // base64再次加密（为了消除乱码）
        return new String(Base64.getEncoder().encode(encryptBytes), CHARSET);
    }

    /**
     * DES解密数据
     *
     * @param content 待解密数据
     * @param key     解密KEY
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key) throws Exception {
        // 待加密数据字节数组
        byte[] byteContent = content.getBytes(CHARSET);
        // 创建密匙KEY
        Key k = generateKey(key);
        // 实例化密码器
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 偏移变量
        IvParameterSpec ips = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
        // 初始化为解密模式的密码器
        cipher.init(Cipher.DECRYPT_MODE, k, ips);
        // DES解密前先base64解密
        return new String(cipher.doFinal(Base64.getDecoder().decode(byteContent)), CHARSET);
    }

    /**
     * 生成密钥
     *
     * @param key 原始密匙KEY
     * @return
     * @throws Exception
     */
    public static String initKey(String key) throws Exception {
        // return initKeyWithMd5(key);
        return initKeyWithPadding(key);
    }

    /**
     * DES加密文件
     *
     * @param srcFile
     * @param destFile
     * @param key
     * @throws Exception
     */
    public static void encryptFile(String srcFile, String destFile, String key) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(key), iv);
        InputStream is = new FileInputStream(srcFile);
        OutputStream out = new FileOutputStream(destFile);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        cis.close();
        is.close();
        out.close();
    }

    /**
     * DES解密文件
     *
     * @param srcFile
     * @param destFile
     * @param key
     * @throws Exception
     */
    public static void decryptFile(String srcFile, String destFile, String key) throws Exception {
        // File file = new File(destFile);
        // if (!file.exists()) {
        //     file.getParentFile().mkdirs();
        //     file.createNewFile();
        // }
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(key), iv);
        InputStream is = new FileInputStream(srcFile);
        OutputStream out = new FileOutputStream(destFile);
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = is.read(buffer)) >= 0) {
            cos.write(buffer, 0, r);
        }
        cos.close();
        is.close();
        out.close();
    }

    /**
     * 生成密钥Key
     *
     * @param key 加密KEY
     * @return
     * @throws Exception
     */
    private static Key generateKey(String key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        // SecretKey secretKey = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM);
        return secretKey;
    }

    /**
     * 通过MD5的方式，生成密钥
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static String initKeyWithMd5(String password) throws Exception {
        // 得到一个信息摘要器
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(password.getBytes());
        StringBuffer buffer = new StringBuffer();
        // 把每一个byte 做一个与运算 0xff;
        for (byte b : digest) {
            // 与运算
            int num = b & 0xff;// 加盐
            String str = Integer.toHexString(num);
            if (str.length() == 1) {
                buffer.append("0");
            }
            buffer.append(str);
        }
        String hash = new String(buffer.toString().getBytes("UTF-8"), "UTF-8");
        return hash.substring(0, 24);
    }

    /**
     * 不足24位时，通过填充的方式生产密钥
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static String initKeyWithPadding(String password) throws Exception {
        if (password.length() >= 24) {
            return password;
        } else {
            byte[] bytes = password.getBytes();
            byte[] result = new byte[24];
            System.arraycopy(bytes, 0, result, 0, bytes.length);
            return new String(result);
        }
    }
}
