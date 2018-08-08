package com.example.mockdemo;

import com.example.mockdemo.controller.GameController;
import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockDemoApplicationTests {
    public static String url = "/game/getGameById";
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
    public void test() throws Exception {

        Game game = new Game.Builder()
                .withId(1)
                .withName("coco")
                .build();
        Mockito.when(gameService.getGameById(Mockito.anyInt())).thenReturn(game);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .param("id", "11"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println(mvcResult);
    }

}
