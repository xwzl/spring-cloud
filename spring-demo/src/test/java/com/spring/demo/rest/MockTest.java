package com.spring.demo.rest;

import com.spring.demo.DemoApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author xuweizhi
 * @since 2019/09/17 22:52
 */
public class MockTest extends DemoApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 无参测试
     */
    @Test
    public void testMock() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rest/get")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 有参数测试
     */
    @Test
    public void testMockParam() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rest/get/param")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("param", "你好啊！MocKMvc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testMockEntity() throws Exception{
        MvcResult noticeTitle = mockMvc.perform(MockMvcRequestBuilders.get("rest/get/entity/1234565")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("noticeTitle", "你好啊！MocKMvc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(noticeTitle);
    }

}
