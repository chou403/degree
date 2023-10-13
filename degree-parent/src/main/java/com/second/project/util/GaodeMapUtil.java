package com.second.project.util;//package com.second.project.util;
//
//import cn.hutool.core.io.FileUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Maps;
//import com.second.common.util.HttpUtil;
//import com.second.project.other.gaode.*;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.utils.URIBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cglib.beans.BeanMap;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.ServletOutputStream;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * {@code @description} 高德地图工具类
// */
//@Component
//@Slf4j
//public class GaodeMapUtil {
//
//
//    @Value("${gaodemap.key}")
//    private String key;
//
//    @Value("${gaodemap.pathImg}")
//    private String pathImg;
//
//    /**
//     * 结构化地址信息查询
//     * @param geocodeRequestVo
//     * @return
//     */
//    @ApiOperation(value = "结构化地址信息查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "address", value = "结构化地址信息", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "city", value = "指定查询城市 城市的中文或中文全拼 ", required = false, dataType = "String")
//    })
//    public R getGeocodes(GeocodeRequestVo geocodeRequestVo) {
//        log.info("==>查询结构化地址:" + geocodeRequestVo.getAddress());
//        //是否支持批量查询 true:支持 false:不支持
//        geocodeRequestVo.setBatch(false);
//        //支持的格式XML,JSON
//        geocodeRequestVo.setOutput(GaodeMapConstant.JSON);
//        Map<String, Object> stringObjectMap = beanToMap(geocodeRequestVo);
//        stringObjectMap.put(GaodeMapConstant.KEY, key);
//        String resultJson = null;
//        try {
//            resultJson = HttpUtil.httpGet(GaodeMapConstant.GEOCODE_GEO, stringObjectMap);
//        } catch (Exception e) {
//            log.error("请求服务异常:{}", e.getMessage());
//            return R.error("请求服务异常").setCode(SystemConstant.SERVER_ERROR_CODE);
//        }
//        JSONObject jsonObject = JSON.parseObject(resultJson);
//        if (Objects.equals(GaodeMapConstant.SUCCESS, jsonObject.get("status"))) {
//            String geocodes = JSON.toJSONString(jsonObject.get("geocodes"));
//            List<Geocodes> geocodes1 = JSON.parseArray(geocodes, Geocodes.class);
//            return R.ok(geocodes1.get(0)).setCode(SystemConstant.SUCCESS_CODE);
//        } else {
//            log.info("获取地址信息失败,原因:{}", jsonObject.get("info"));
//            return R.error(jsonObject.get("info").toString()).setCode(SystemConstant.SERVER_ERROR_CODE);
//        }
//    }
//
//    /**
//     * 通过经纬度,获取地理位置信息
//     * @param geocodeRequestVo
//     * @return
//     */
//    @ApiOperation(value = "获取逆地理编码信息列表", notes = "朱传捷")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "location", value = "经纬度(格式:116.481488,39.990464)", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "poitype", value = "返回附近POI类型(extensions 参数为 all 时才生效)", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "radius", value = "搜索半径", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "extensions", value = "返回结果控制(base和all)", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "roadlevel", value = "道路等级", required = false, dataType = "String")
//    })
//    public R getReGeocodes(GeocodeRequestVo geocodeRequestVo) {
//        //是否支持批量查询 true:支持 false:不支持
//        geocodeRequestVo.setBatch(false);
//        //支持的格式XML,JSON
//        geocodeRequestVo.setOutput(GaodeMapConstant.JSON);
//        Map<String, Object> stringObjectMap = beanToMap(geocodeRequestVo);
//        stringObjectMap.put(GaodeMapConstant.KEY, key);
//        try {
//            String resultJson = HttpUtil.httpGet(GaodeMapConstant.GEOCODE_REGEO, stringObjectMap);
//            JSONObject jsonObject = JSON.parseObject(resultJson);
//            if (Objects.equals(GaodeMapConstant.SUCCESS, jsonObject.get("status"))) {
//                return R.ok(jsonObject.get("regeocode")).setCode(SystemConstant.SUCCESS_CODE);
//            } else {
//                return R.error(jsonObject.get("info").toString()).setCode(SystemConstant.SERVER_ERROR_CODE);
//            }
//        } catch (Exception e) {
//            log.error("通过经纬度获取地址失败:{}", e.getMessage());
//            return R.error("获取地址失败").setCode(SystemConstant.SERVER_ERROR_CODE);
//        }
//    }
//
//    /**
//     * 路线规划
//     * @param directionRequestVo
//     * @return
//     */
//    @ApiOperation(value = "地图路线规划,支持步行、公交、驾车方式")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "directionType", value = "交通工具类型1:步行 2:公交 3:驾车", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "origin", value = "出发点经纬度", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "destination", value = "终点经纬度", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "city", value = "城市/跨城规划时的起点城市,directionType = 2时必传", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "strategy", value = "换乘策略", required = false, dataType = "Integer")
//    })
//    public R getDirection(DirectionRequestVo directionRequestVo) {
//        if (!StringUtils.hasText(directionRequestVo.getOrigin())) {
//            return R.error(GaodeMapConstant.ERR_ORIGIN_PARAM_NULL).setCode(SystemConstant.PARAM_INCORRECT_CODE);
//        }
//        if (!StringUtils.hasText(directionRequestVo.getDestination())) {
//            return R.error(GaodeMapConstant.ERR_DESTINATION_PARAM_NULL).setCode(SystemConstant.PARAM_INCORRECT_CODE);
//        }
//        //判断路线规划类型,目前只支持3种,strategy=2时,city城市名称
//        String reqUrl = null;
//        switch (directionRequestVo.getStrategy()) {
//            case 1:
//                reqUrl = GaodeMapConstant.DIRECTION_WALKING;
//                break;
//            case 2:
//                if (!StringUtils.hasText(directionRequestVo.getCity())) {
//                    return R.error(GaodeMapConstant.ERR_CITY_PARAM_NULL).setCode(SystemConstant.PARAM_INCORRECT_CODE);
//                }
//                reqUrl = GaodeMapConstant.DIRECTION_INTEGRATED;
//                break;
//
//            case 3:
//                reqUrl = GaodeMapConstant.DIRECTION_DRIVING;
//                break;
//            default:
//                return R.error(GaodeMapConstant.ERR_DIRECTIONTYPE_PARAM_NULL).setCode(SystemConstant.PARAM_INCORRECT_CODE);
//        }
//        Map<String, Object> stringObjectMap = beanToMap(directionRequestVo);
//        stringObjectMap.put(GaodeMapConstant.KEY, key);
//        try {
//            String rusultStr = HttpUtil.httpGet(reqUrl, stringObjectMap);
//            JSONObject jsonObject = JSON.parseObject(rusultStr);
//            if (Objects.equals(GaodeMapConstant.SUCCESS, jsonObject.get("status"))) {
//                return R.ok(jsonObject.get("route"));
//            } else {
//                log.error("查询路线规划失败:{}", jsonObject.get("info").toString());
//                return R.error(jsonObject.get("info").toString()).setCode(SystemConstant.SERVER_ERROR_CODE);
//            }
//        } catch (Exception e) {
//            log.error("查询路线规划异常:{}", e.getMessage());
//            return R.error("查询路线规划异常").setCode(SystemConstant.SERVER_ERROR_CODE);
//        }
//    }
//
//    /**
//     * 静态地图
//     * @param staticMapRequest
//     */
//    @ApiOperation(value = "生成静态地图")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "locations", value = "经纬度集合", required = true, dataType = "List"),
//            @ApiImplicitParam(name = "zoom", value = "地图缩放级别:[1,17]", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "size", value = "图片宽度*图片高度。最大值为1024*1024", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "scale", value = "清晰度", required = true, dataType = "Integer")
//    })
//    public void staticMap(StaticMapRequest staticMapRequest, HttpServletResponse response) {
//
//        //设置地图缩放级别
//        staticMapRequest.setZoom(staticMapRequest.getZoom());
//        //地图大小
//        staticMapRequest.setSize(staticMapRequest.getSize());
//        //设置高清
//        staticMapRequest.setScale(staticMapRequest.getScale());
//        List<String> localhosts = staticMapRequest.getLocalhosts();
//        //设置默认第一个和最后一个点,标注,起,终
//        Markers marker1 = Markers
//                .builder()
//                .color("0xFF0000")
//                .size("mid")
//                .label("起:" + localhosts.get(0))
//                .build();
//        Markers markers2 = Markers
//                .builder()
//                .size("mid")
//                .color("0xFF0000")
//                .label("终:" + localhosts.get(localhosts.size() - 1))
//                .build();
//        //拼接markers属性
//        String setMarkers1 = marker1.getSize() + "," + marker1.getColor() + "," + marker1.getLabel();
//        String setMarkers2 = markers2.getSize() + "," + markers2.getColor() + "," + markers2.getLabel();
//        staticMapRequest.setMarkers(setMarkers1 + "|" + setMarkers2);
//        //绘制折线
//        Paths paths = Paths
//                .builder()
//                .weight(5)
//                .color("0xFF0000")
//                .transparency("1")
//                .build();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String localhost : localhosts) {
//            stringBuilder.append(localhost + ";");
//        }
//        String s = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
//        String path = paths.getWeight() + "," + paths.getColor() + "," + paths.getTransparency() + "," + "," + ":" + s;
//        staticMapRequest.setPaths(path);
//        Map<String, Object> stringObjectMap = beanToMap(staticMapRequest);
//        stringObjectMap.put(GaodeMapConstant.KEY, key);
//        try {
//            // 转换请求参数
//            List<NameValuePair> pairs = HttpUtil.covertParams2NVPS(stringObjectMap);
//            // 装载请求地址和参数
//            URIBuilder ub = new URIBuilder();
//            ub.setPath(GaodeMapConstant.STATUC_MAP);
//            ub.setParameters(pairs);
//            String s1 = ub.toString();
//            String compress = compress(s1, pathImg);
//            exportImg(response, pathImg + compress);
//        } catch (Exception e) {
//            log.error("下载图片异常" + e.getMessage());
//        }
//    }
//
//    /**
//     * bean -> map 实体类转map
//     * @param bean
//     * @param <T>
//     * @return
//     */
//    public static <T> Map<String, Object> beanToMap(T bean) {
//        Map<String, Object> map = Maps.newHashMap();
//        if (bean != null) {
//            BeanMap beanMap = BeanMap.create(bean);
//            for (Object key : beanMap.keySet()) {
//                if (Objects.nonNull(beanMap.get(key)) && !(Objects.equals("", beanMap.get(key)))) {
//                    map.put(key + "", beanMap.get(key));
//                }
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 下载地图
//     * @param url
//     * @param path
//     * @return
//     */
//    public static String compress(String url, String path) {
//        try {
//            String imageName = "map.png";
//            File file = new File(path + imageName);
//            //判断文件是否已经存在
//            if (file.exists()) {
//                file.delete();
//            }
//            file.createNewFile();
//            URL uri = new URL(url);
//            InputStream in = uri.openStream();
//            FileOutputStream fo = new FileOutputStream(file);
//            byte[] buf = new byte[1024];
//            int length = 0;
//            while ((length = in.read(buf, 0, buf.length)) != -1) {
//                fo.write(buf, 0, length);
//            }
//            in.close();
//            fo.close();
//            return imageName;
//        } catch (Exception e) {
//            log.error("下载失败", e.getMessage());
//            return null;
//        }
//
//    }
//
//    /**
//     * 输出图片
//     * @param response
//     * @param filePath
//     */
//    public static void exportImg(HttpServletResponse response, String filePath) {
//        File file = new File(filePath);
//        response.setCharacterEncoding("utf-8");
//        //设置响应的内容类型
//        response.setContentType("text/plain");
//        //设置文件的名称和格式
//        response.setHeader("content-type", "application/octet-stream");
//        response.setContentType("application/octet-stream");
//        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
//        BufferedOutputStream buff = null;
//        ServletOutputStream outStr = null;
//        try {
//            outStr = response.getOutputStream();
//            buff = new BufferedOutputStream(outStr);
//            buff.write(FileUtil.readBytes(filePath));
//            buff.flush();
//            buff.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("文件导出异常：", e);
//        } finally {
//            try {
//                buff.close();
//            } catch (Exception e) {
//                log.error("流关闭异常：", e);
//            }
//            try {
//                outStr.close();
//            } catch (Exception e) {
//                log.error("流关闭异常：", e);
//            }
//        }
//    }
//
//}