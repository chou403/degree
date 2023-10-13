package com.second.project.other.gaode;//package com.second.project.other.gaode;
//
//import io.swagger.annotations.ApiModel;
//import lombok.*;
//
///**
// * {@code @description}
// */
//@Data
//@ApiModel(description = "地理名称请求视图")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class GeocodeRequestVo {
//
//    //TODO: 地理编码参数
//    /**
//     * 结构化地址信息
//     * 如：北京市朝阳区阜通东大街6号。如果需要解析多个地址的话，
//     * 请用"|"进行间隔，并且将 batch 参数设置为 true，
//     * 最多支持 10 个地址进进行"|"分割形式的请求。
//     */
//    private String address;
//
//    /**
//     * 城市名称
//     * 指定城市的中文（如北京）、指定城市的中文全拼（beijing）、
//     * citycode（010）、adcode（110000），不支持县级市。
//     */
//    private String city;
//
//    /**
//     * 批量查询控制
//     * batch 参数设置为 true 时进行批量查询操作，最多支持 10 个地址进行批量查询。
//     * batch 参数设置为 false 时进行单点查询，此时即使传入多个地址也只返回第一个地址的解析查询结果。
//     */
//    private Boolean batch;
//
//    /**
//     * 返回数据格式类型
//     * 可选输入内容包括：JSON，XML
//     */
//    private String output;
//
//    /**
//     * 数字签名
//     */
//    private String sig;
//
//    /**
//     * 回调函数
//     */
//    private String callback;
//
//    //TODO:逆地理编码参数
//
//    /**
//     * 经纬度坐标
//     * 经度在前，纬度在后，经纬度间以“,”分割，经纬度小数点后不要超过 6 位
//     */
//    private String location;
//
//    /**
//     * 搜索半径
//     * radius取值范围在0~3000，默认是1000。单位：米
//     */
//    private String radius;
//
//    /**
//     * 返回结果控制
//     * extensions 参数默认取值是 base，也就是返回基本地址信息；
//     * extensions 参数取值为 all 时会返回基本地址信息、附近 POI 内容、道路信息以及道路交叉口信息。
//     */
//    private String extensions;
//
//    /**
//     * 道路等级
//     * 以下内容需要 extensions 参数为 all 时才生效。
//     * 可选值：0，1
//     * 当roadlevel=0时，显示所有道路
//     * 当roadlevel=1时，过滤非主干道路，仅输出主干道路数据
//     */
//    private String roadlevel;
//
//    /**
//     * 是否优化POI返回顺序
//     * 以下内容需要 extensions 参数为 all 时才生效。
//     * homeorcorp 参数的设置可以影响召回 POI 内容的排序策略，目前提供三个可选参数：
//     * 0：不对召回的排序策略进行干扰。
//     * 1：综合大数据分析将居家相关的 POI 内容优先返回，即优化返回结果中 pois 字段的poi顺序。
//     * 2：综合大数据分析将公司相关的 POI 内容优先返回，即优化返回结果中 pois 字段的poi顺序。
//     */
//    private String homeorcorp;
//}