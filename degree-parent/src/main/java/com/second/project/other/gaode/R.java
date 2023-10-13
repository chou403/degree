package com.second.project.other.gaode;//package com.second.project.other.gaode;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.ToString;
//
//import java.io.Serializable;
//
///**
// * 返回类型
// */
//@ApiModel
//@ToString
//public class R<T> implements Serializable {
//
//    private static final long serialVersionUID = -6287952131441663819L;
//
//    /**
//     * 编码
//     */
//    @ApiModelProperty(value = "响应码", example = "200")
//    private int code = 200;
//
//    /**
//     * 成功标志
//     */
//    @ApiModelProperty(value = "成功标志", example = "true")
//    private Boolean success;
//
//    /**
//     * 返回消息
//     */
//    @ApiModelProperty(value = "返回消息说明", example = "操作成功")
//    private String msg = "操作成功";
//
//    /**
//     * 返回数据
//     */
//    @ApiModelProperty(value = "返回数据")
//    private T data;
//
//    /**
//     * 创建实例
//     *
//     * @return
//     */
//    public static R instance() {
//        return new R();
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public R setCode(int code) {
//        this.code = code;
//        return this;
//    }
//
//    public Boolean getSuccess() {
//        return success;
//    }
//
//    public R setSuccess(Boolean success) {
//        this.success = success;
//        return this;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public R setMsg(String msg) {
//        this.msg = msg;
//        return this;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public R setData(T data) {
//        this.data = data;
//        return this;
//    }
//
//    public static R ok() {
//        return R.instance().setSuccess(true);
//    }
//
//    public static R ok(Object data) {
//        return ok().setData(data);
//    }
//
//    public static R ok(Object data, String msg) {
//        return ok(data).setMsg(msg);
//    }
//
//    public static R error() {
//        return R.instance().setSuccess(false);
//    }
//
//    public static R error(String msg) {
//        return error().setMsg(msg);
//    }
//
//    /**
//     * 无参
//     */
//    public R() {
//    }
//
//    public R(int code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//
//    public R(int code, T data) {
//        this.code = code;
//        this.data = data;
//    }
//
//    /**
//     * 有全参
//     *
//     * @param code
//     * @param msg
//     * @param data
//     * @param success
//     */
//    public R(int code, String msg, T data, Boolean success) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//        this.success = success;
//    }
//
//    /**
//     * 有参
//     *
//     * @param code
//     * @param msg
//     * @param data
//     */
//    public R(int code, String msg, T data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }
//}