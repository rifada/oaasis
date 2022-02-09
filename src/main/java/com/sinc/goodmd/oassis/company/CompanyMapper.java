package com.sinc.goodmd.oassis.company;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<Company> findAll();
}
