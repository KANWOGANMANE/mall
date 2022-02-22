package com.sjq.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Kemp
 * @create 2022/1/24 11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {

    private String code;
    private String mag;

}
