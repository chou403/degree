package com.second.project.other.gaode;//package com.second.project.other.gaode;
//
//import io.swagger.annotations.ApiModel;
//import lombok.*;
//
///**
// * {@code @description}
// */
//@Data
//@ApiModel(description = "折线视图")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Paths {
//
//    /**
//     * 线条粗细。
//     * 可选值： [2,15]
//     */
//    private Integer weight;
//
//    /**
//     * 折线颜色。 选值范围：[0x000000, 0xffffff]
//     * 例如：
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
//     * 透明度。
//     * 可选值[0,1]，小数后最多2位，0表示完全透明，1表示完全不透明。
//     */
//    private String transparency;
//
//    /**
//     * 多边形的填充颜色，此值不为空时折线封闭成多边形。取值规则同color
//     */
//    private String fillcolor;
//
//    /**
//     * 填充面透明度。
//     * 可选值[0,1]，小数后最多2位，0表示完全透明，1表示完全不透明
//     */
//    private String fillTransparency;
//}