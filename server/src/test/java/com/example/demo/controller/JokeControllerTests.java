package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class JokeControllerTests {
    private static final Logger logger = LoggerFactory.getLogger(JokeControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JokeRepository jokeRepository;

    @Test
    public void getAllJokesTest() throws Exception {
        mockMvc.perform(get("/api/jokes"))
                .andExpect(status().isOk());
    }

    /**
     * joke 增删改查
     */
    @Test
    public void jokeCURD() throws Exception {
        Joke joke = new Joke();
        joke.setTitle("title");
        joke.setContent("content");
        JSONObject object = new JSONObject();
        object.put("title", joke.getTitle());
        object.put("content", joke.getContent());
        //---新建
        MvcResult mvcResult = mockMvc.perform(post("/api/jokes")
                .content(object.toJSONString())
                .contentType(MediaType.APPLICATION_JSON))   //指定传输的类型 "application/json"
                .andExpect(status().isOk())
                .andReturn();
        String resStr = mvcResult.getResponse().getContentAsString();
        JSONArray array = JSONArray.parseArray(resStr);

        assertThat(array.size()).isEqualTo(1);
        //查看title 的值
        assertThat(((JSONObject) array.get(0)).getString("title"))
                .isEqualTo("title");
        //----修改
        int id = ((JSONObject) array.get(0)).getInteger("id");
        joke.setId(id);
        joke.setTitle("TITLE");
        mvcResult = mockMvc.perform(put("/api/jokes/" + id)
                .content(JSONObject.toJSONString(joke))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //将返回的response放置到JsonObject
        object = JSONObject.parseObject(mvcResult.getResponse().getContentAsString());
        assertThat(object.getString("title")).isEqualTo("TITLE");
        //----删除
        mockMvc.perform(delete("/api/jokes/" + id))
                .andExpect(status().isOk());

        mvcResult = mockMvc.perform(get("/api/jokes"))
                .andExpect(status().isOk())
                .andReturn();
        array = JSONArray.parseArray(mvcResult.getResponse().getContentAsString());
        //若删除成功，那么array size = 0
        assertThat(array.size()).isEqualTo(0);
    }

    @Test
    public void exceptionTest() {

    }

    @After
    public void removeAllJokes() {
        jokeRepository.deleteAll();
    }
}
