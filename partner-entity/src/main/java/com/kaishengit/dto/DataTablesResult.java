package com.kaishengit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sunny on 2017/2/22.
 */
@Data
@AllArgsConstructor
public class DataTablesResult {
    private String draw;
    private Long recordsTotal;//总数量
    private Long recordsFiltered;//过滤后所有的数量
    private Object data;
}
