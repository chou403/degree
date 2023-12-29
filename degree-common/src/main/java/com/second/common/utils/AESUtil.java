package com.second.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 1
 * {@code @author}  chou401
 * {@code @date}    2023/5/18 10:28
 */
@Slf4j
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 随机生成密钥
     *
     * @return
     */
    public static String getAESRandomKey() {
        SecureRandom random = new SecureRandom();
        long randomKey = random.nextLong();
        return String.valueOf(randomKey);
    }

//    public static void main(String[] args) {
//        System.out.println(getAESRandomKey());
//    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) throws Exception {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return byte2Base64(result);
        } catch (Exception ex) {
            log.error("加密失败", ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            //执行操作
            byte[] result = cipher.doFinal(base642Byte(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("解密失败", ex);
        }

        return null;
    }


    /**
     * 字节数组转Base64编码
     *
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(bytes));
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Key
     * @return
     */
    public static byte[] base642Byte(String base64Key) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64Key);
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // 此类提供加密的强随机数生成器 (RNG)，该实现在windows上每次生成的key都相同，但是在部分linux或solaris系统上则不同。
            // SecureRandom random = new SecureRandom(key.getBytes());
            // 指定算法名称，不同的系统上生成的key是相同的。
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            //AES 要求密钥长度为 128
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            log.info("生成加密秘钥异常！", ex);
        }
        return null;
    }

}
