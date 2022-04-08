package com.sjq.edu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author Kemp
 * @create 2022/4/1 12:20
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String lonesubjectName;

    @ExcelProperty(index = 1)
    private String ltwosubjectName;
}
