package com.spring.demo.rest;

import com.spring.demo.DemoApplicationTest;
import com.spring.demo.model.vos.DataVO;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.operation.Parameters;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

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
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("which", "请求头测试"))
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
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("param", "你好啊！MocKMvc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("\"你好啊！MocKMvc\""))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 实体对象
     */
    @Test
    public void testPojo() throws Exception {
        //
        MultiValueMap<String, String> params = new Parameters();
        params.add("noticeId", "1111");
        params.add("noticeTitle", "22222");
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/get/pojo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("noticeId").value("1111"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试 哈哈
     */
    @Test
    public void testMockEntity() throws Exception {
        MvcResult noticeTitle = mockMvc.perform(MockMvcRequestBuilders.get("/rest/get/entity/11111")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("noticeTitle", "你好啊！MocKMvc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(noticeTitle.getResponse().getContentAsString());
        String expect = "{\"noticeContent\":null,\"noticeCreateTime\":null,\"noticeId\":1111,\"noticeImg\":null,\"noticeTitle\":\"你好啊！MocKMvc\",\"noticeUpdateTime\":null}";
        JSONAssert.assertEquals(expect, noticeTitle.getResponse().getContentAsString(), false);
        System.out.println('"');
    }

    /**
     * 优雅的方式构造 MockMvc 请求
     */
    @Test
    public void elegantRequest() throws Exception {

        DataVO dataVO = DataVO.builder().noticeId(1111).noticeContent("2222").noticeTitle("3333").noticeImg("6666").build();
        String json = "{\"noticeContent\":\"2222\",\"noticeId\":1111,\"noticeImg\":\"6666\",\"noticeTitle\":\"3333\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rest/post/entity")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatus());

        System.out.println(response.getContentAsString());
    }


}
