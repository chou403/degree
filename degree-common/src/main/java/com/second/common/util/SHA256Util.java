package com.second.common.util;

import org.apache.http.client.utils.DateUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class SHA256Util {

    public static String getSHA256StrJava(String str) throws Exception {
        MessageDigest messageDigest;
        String encodeStr = "";
        messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        encodeStr = byte2Hex(messageDigest.digest());
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    public static String buildAppSecret2(Date now, String appSecret3) throws Exception {
        String formatDate = DateUtils.formatDate(now, "yyyy-MM-dd");
        System.out.println(formatDate);
        String content = SHA256Util.getSHA256StrJava(SHA256Util.getSHA256StrJava(formatDate) + appSecret3);
        return URLEncoder.encode(content, StandardCharsets.UTF_8);
    }

    public static String sign(Map<String, Object> map, Date now, String appSecret3, String appSecret) throws Exception {
        Map<String, Object> tmp = new TreeMap<>(map);
        String appSecret2 = buildAppSecret2(now, appSecret3);
        System.out.println("sign appSecret2 :" + appSecret2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("secretKey2").append("=").append(appSecret2).append("&");
        tmp.forEach((k, v) -> stringBuffer.append(k).append("=").append(v).append("&"));
        stringBuffer.append("secretKey").append("=").append(appSecret);
        System.out.println(stringBuffer);
        return SHA256Util.getSHA256StrJava(stringBuffer.toString());
    }

//    public static void main(String[] args) {
//        Map<String,Object> parm = new HashMap();
//        List<String> list = new ArrayList<>();
//        list.add("123");
//        list.add("122");
//        list.add("1234");
//        list.add("125");
//        parm.put("list", list);
//        Map<String,Object> map = new HashMap();
//        try {
//            Date date = new Date();
//            map.put("date", parm);
//            map.put("Time", date.getTime());
//            map.put("sign", sign(parm, date, "a", "b"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Iterator<String> it = map.keySet().iterator();
//        while(it.hasNext()){
//            String key = it.next();
//            Object value = map.get(key);
//            System.out.println(key+"||||"+value);
//        }
//    }
}
