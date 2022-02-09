package com.sinc.goodmd.oassis.config.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonResponse {
    HttpStatus statusCode;
    int rtnCnt;
    String rtnMsg;
    Object result;
}
