package com.sinc.goodmd.oassis.mybatis;


import com.sinc.goodmd.oassis.test.TestMapper;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.sql.DataSourceDefinition;
import java.util.List;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestMybatis {

    @Autowired
    private TestMapper testMapper;

    @DisplayName("1.테스트")
    @Test
     void test_01() {
        testMapper.selectTest();
        //System.out.println(test.get(0).toString());
    }
}
