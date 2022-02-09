package com.sinc.goodmd.oassis.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinc.goodmd.oassis.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class TestAuth {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("1.인증이 필요하지 않은 접근")
    void test01() throws Exception{

        Member testMember = new Member();
        testMember.setUserId("holytiger");
        testMember.setUserPass("1234");
        String body = "{\"userId\":\"holytiger\",\"userPass\":\"1234\"}";
        mockMvc.perform(post("/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("2.인증이 필요한 접근")
    void test02() throws Exception{
        mockMvc.perform(get("/holytiger"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("3.인증이 필요한 접근(Mybatis)")
    void test03() throws Exception{
        mockMvc.perform(get("/test/select"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
