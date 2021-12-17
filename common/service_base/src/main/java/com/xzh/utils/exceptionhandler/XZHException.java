package com.xzh.utils.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author XZH
 * @date 2021年12月13日 15:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XZHException extends RuntimeException {
    private Integer code;
    private String msg;
}
