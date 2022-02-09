package com.sinc.goodmd.oassis.company;

import com.sinc.goodmd.oassis.config.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<CommonResponse> findAll() {
        log.info("CompanyController::findAll");
        CommonResponse response = new CommonResponse();

        try {
            List<Company>companys = companyService.findAll();
            response.setRtnCnt(companys.size());
            response.setResult(companys);
            response.setRtnMsg("success");
            response.setStatusCode(HttpStatus.OK);
        } catch(Exception e) {
            response.setRtnMsg("fail");
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createCompany(@RequestBody List<Company> list) {
        log.info("CompanyController::createCompany");
        log.info(list.toString());
        CommonResponse response = new CommonResponse();

        for(Company com : list){
            log.info(com.toString());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
