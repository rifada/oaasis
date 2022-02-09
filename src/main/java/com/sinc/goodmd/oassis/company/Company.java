package com.sinc.goodmd.oassis.company;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Company {
    private String comCd;
    private String comNm;
    private String solType;
    private String solDetailType;
    private int charge;
    private String cancelYn;
    private String posType;
    private String pdaType;
    private LocalDateTime joinDate;
    private String regId;
    private LocalDateTime regDate;
    private String modId;
    private LocalDateTime modDate;
}
