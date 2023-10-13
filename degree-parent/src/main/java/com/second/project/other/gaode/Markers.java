package com.second.project.other.gaode;//package com.second.project.other.gaode;
//
//import io.swagger.annotations.ApiModel;
//import lombok.*;
//
///**
// * {@code @description}
// */
//@Data
//@ApiModel(description = "标注视图")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Markers {
//
//    /**
//     * 可选值： small,mid,large
//     */
//    private String size;
//
//    /**
//     * 0x000000 black,
//     * 0x008000 green,
//     * 0x800080 purple,
//     * 0xFFFF00 yellow,
//     * 0x0000FF blue,
//     * 0x808080 gray,
//     * 0xffa500 orange,
//     * 0xFF0000 red,
//     * 0xFFFFFF white
//     */
//    private String color;
//
//    /**
//     * 0-9]、[A-Z]、[单个中文字] 当size为small时，图片不展现标注名。
//     * 格式 A:116.31604,39.96491
//     */
//    private String label;
//}