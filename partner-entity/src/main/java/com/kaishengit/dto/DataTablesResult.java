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
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;
}
