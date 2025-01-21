package com.lee.questionnaire.common.result;

import com.lee.questionnaire.common.constant.SystemConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结构体
 *
 * @author Ray
 * @since 2022/1/30
 **/
@Data
public class Result<T> implements Serializable {

    private String code;

    private T data;

    private String msg;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SystemConstant.RESULT_CODE_SUCCESS);
        result.setMsg(SystemConstant.RESULT_MSG_SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failed() {
        return result(SystemConstant.RESULT_CODE_FAIL,SystemConstant.RESULT_MSG_FAIL, null);
    }

    public static <T> Result<T> failed(String msg) {
        return result(SystemConstant.RESULT_CODE_FAIL, msg, null);
    }

    public static <T> Result<T> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return failed();
        }
    }




    private static <T> Result<T> result(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

}
