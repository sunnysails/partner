package com.kaishengit.dto;

import lombok.Data;

/**
 * Created by sunny on 2017/2/18.
 */
@Data
public class AjaxResult {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String status;
    private String message;
    private Object data;

    public AjaxResult(String message) {
        if (message.equals(SUCCESS)) {
            this.status = SUCCESS;
        } else {
            this.status = ERROR;
            this.message = message;
        }
    }

    public AjaxResult(Object data) {
        this.status = SUCCESS;
        this.data = data;
    }


    public AjaxResult(String status, String value) {
        if (status.equals(SUCCESS)) {
            this.status = SUCCESS;
            this.data = value;
        } else {
            this.status = ERROR;
            this.message = value;
        }
    }
}
