package com.example.mockdemo;

import com.alibaba.fastjson.JSON;
import com.example.mockdemo.controller.GameController;
import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockDemoApplicationTests {
    private MockMvc mockMvc;

    private GameController gameController;
    @Mock
    private GameService gameService;

    @Before
    public void init() {

        gameController = new GameController(gameService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(gameController)
                .build();
    }

    @Test
    public void testGetGameById() throws Exception {
        String url = "/game/getGameById";
        Game game = new Game.Builder()
                .withId(1)
                .withName("coco")
                .build();
        Mockito.when(gameService.getGameById(Mockito.anyInt())).thenReturn(game);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .param("id", "11"))
                .andDo(MockMvcResultHandlers.print())// 打印结果
                .andExpect(MockMvcResultMatchers.status().isOk())// 期望状态是200
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());// 响应的json串中需要包含name节点
        MvcResult mvcResult = resultActions.andReturn();
    }


    @Test
    public void testUpdateGame() throws Exception {
        String url = "/game/updateGame";
        Game game = new Game.Builder()
                .withId(1)
                .withName("coco")
                .build();
        this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .content(JSON.toJSONString(game))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    public void testSaveGame() throws Exception {
        String url = "/game/saveGame";
        Game game = new Game.Builder()
                .withId(2)
                .withName("joker")
                .build();
        this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(JSON.toJSONString(game))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void testUpdateGameWithNullEntity() throws Exception {
        String url = "/game/updateGame";
        Game game = new Game.Builder()
                .withName("joker")
                .build();
        this.mockMvc.perform(MockMvcRequestBuilders.put(url)
                .content(JSON.toJSONString(game))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());

    }
}
