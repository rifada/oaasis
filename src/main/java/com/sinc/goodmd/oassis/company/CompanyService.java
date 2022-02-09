package com.sinc.goodmd.oassis.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public List<Company> findAll(){
        return companyMapper.findAll();
    }
}
