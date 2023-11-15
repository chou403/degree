package com.second.common.utils;

import com.second.common.bean.GaoDeEnum;
import com.second.common.bean.reponse.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/15
 * {@code @description} 高德 util
 */
@Slf4j
public class GaoDeMapUtil {

    private static final String KEY = "840fa6201b5d6841e3f5a30f59db43f9";

    /**
     * 根据地址名称得到两个地址间的距离
     *
     * @param start
     * @param end
     * @return
     */
    public static Long getDistanceByAddress(String start, String end) {
        String startLonLat = getLonLat(start).getData().toString();
        String endLonLat = getLonLat(end).getData().toString();
        return Long.valueOf(getDistance(startLonLat, endLonLat).getData().toString());
    }

    /**
     * 地址转换为经纬度
     *
     * @param address
     * @return
     */
    public static Result getLonLat(String address) {
        try {
            // 返回输入地址address的经纬度信息, 格式是 经度,纬度
            String url = "https://restapi.amap.com/v3/geocode/geo?address=" + address + "&key=" + KEY;

            // 高德接口返回的是JSON格式的字符串
            String queryResult = HttpUtil.httpGet(url);
            Map parseToObject = JsonHelper.parseToMap(queryResult);
            Map toJson = (Map) JsonHelper.parseToList(parseToObject.get("geocodes")).get(0);
            String LngAndLat = toJson.get("location").toString();
            log.info("经纬度为：" + LngAndLat);
            return Result.success(LngAndLat, "经纬度转换成功！");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(e.toString());
        }
    }

    /**
     * 将经纬度 转换为 地址
     *
     * @param longitude
     * @param latitude
     * @return
     * @throws Exception
     */
    public static Result getAddress(String longitude, String latitude) {
        String url;
        try {
            url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + longitude + "," + latitude
                    + "&key=" + KEY + "&radius=0&extensions=base";

            log.info("经度" + longitude);
            log.info("纬度：" + latitude);
            log.info("url:" + url);

            // 高德接口返回的是JSON格式的字符串
            String queryResult = HttpUtil.httpGet(url);
            if (StringUtil.isNullOrEmpty(queryResult)) {
                return Result.error("查询结果为空");
            }

            // 将获取结果转为 json 数据
            Map obj = JsonHelper.parseToMap(queryResult);
            if (obj.get(GaoDeEnum.STATUS.getCode()).toString().equals(GaoDeEnum.INT_ONE.getCode())) {
                // 如果没有返回-1
                Map reGeoCode = (Map) obj.get(GaoDeEnum.RE_GEO_CODE.getCode());
                if (reGeoCode.size() > 0) {
                    // 在regeocode中拿到 formatted_address 具体位置
                    String formatted = reGeoCode.get("formatted_address").toString();
                    return Result.success(formatted, "地址获取成功！");

                } else {
                    return Result.error("未找到相匹配的地址！");
                }
            } else {
                return Result.error("请求错误！");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return Result.error("系统未知异常，请稍后再试");
        }
    }

    /**
     * 根据两个定位点的经纬度算出两点间的距离
     *
     * @param startLonLat
     * @param endLonLat
     * @return
     */
    public static Result getDistance(String startLonLat, String endLonLat) {
        try {
            // 返回起始地startAddr与目的地endAddr之间的距离，单位：米
            String queryUrl =
                    "http://restapi.amap.com/v3/distance?key=" + KEY + "&origins=" + startLonLat
                            + "&destination="
                            + endLonLat;
            String queryResult = HttpUtil.httpGet(queryUrl);
            Map job = JsonHelper.parseToMap(queryResult);
            Map jobO = (Map) JsonHelper.parseToList(job.get("results")).get(0);
            long result = Long.parseLong(jobO.get("distance").toString());
            log.info("距离：" + result);
            return Result.success(result, "距离计算成功！");
        } catch (Exception e) {
            return Result.error(e.toString());
        }


    }

    public static void main(String[] args) {
        String address = "北京市朝阳区阜通东大街6号";
        Result location = getLonLat(address);
        System.out.println("经纬度：" + location.getData());

        Result lonLat = getAddress("116.482086", "39.990496");
        System.out.println("地址：" + lonLat.getData());

        Result distance = getDistance("116.482086,39.990496", "116.482086,39.990496");
        System.out.println("经纬度距离：" + distance.getData());

        Long distanceAddress = getDistanceByAddress("天安门", "故宫");
        System.out.println("地址距离：" + distanceAddress);
    }

}
