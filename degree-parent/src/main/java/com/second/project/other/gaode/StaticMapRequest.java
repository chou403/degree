package com.second.project.other.gaode;//package com.second.project.other.gaode;
//
//import io.swagger.annotations.ApiModel;
//import lombok.*;
//
//import java.util.List;
//
///**
// * {@code @description} 绘制静态地图请求实体
// */
//@Data
//@ApiModel(description = "绘制静态地图请求视图")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class StaticMapRequest {
//
//    /**
//     * 路线主键Id
//     */
//    private Long id;
//
//    /**
//     * 地图中心点 中心点坐标。
//     * 规则：经度和纬度用","分隔 经纬度小数点后不得超过6位。
//     */
//    private String location;
//
//    /**
//     * 地图缩放级别:[1,17]
//     */
//    private Integer zoom;
//
//    /**
//     * 图片宽度*图片高度。最大值为1024*1024
//     */
//    private String size;
//
//    /**
//     * 1:返回普通图；
//     * 2:调用高清图，图片高度和宽度都增加一倍，zoom也增加一倍（当zoom为最大值时，zoom不再改变）。
//     */
//    private Integer scale;
//
//    /**
//     * 标注
//     */
//    private String markers;
//
//    /**
//     * 折线
//     */
//    private String paths;
//
//
//    /**
//     * 经纬度集合
//     */
//    private List<String> localhosts;
//
//}