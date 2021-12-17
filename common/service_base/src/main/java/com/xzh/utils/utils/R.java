package com.xzh.utils.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XZH
 * @date 2021年12月13日 16:00
 */
@Data
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("操作成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(500);
        r.setMessage("操作失败");
        return r;
    }

    public R data(String key, Object value) {
        R r = new R();
        data.put(key, value);
        r.setData(data);
        return r;
    }

}
