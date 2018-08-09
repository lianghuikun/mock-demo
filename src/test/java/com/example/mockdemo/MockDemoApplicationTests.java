package com.example.mockdemo;

import com.alibaba.fastjson.JSON;
import org.springframework.util.*;
import com.example.mockdemo.controller.GameController;
import com.example.mockdemo.domain.City;
import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void testList() {

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
    public void testGetGameById() throws Exception {
        String url = "/game/getGameById";
        Game game = new Game.Builder()
                .withId(1)
                .withName("coco")
                .withCityList(Arrays.asList(new City.Builder()
                        .withId(100)
                        .withCityName("wuXi-china")
                        .builder()))
                .build();
        when(gameService.getGameById(anyInt())).thenReturn(game);
        this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .param("id", "11"))
                .andDo(MockMvcResultHandlers.print())// 打印结果
                .andExpect(MockMvcResultMatchers.status().isOk())// 期望状态是200
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("coco")))// 响应报文中包含字符串 coco
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())// 响应的json串中需要包含name节点
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityList").exists())// 返回结果必须包含list
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("coco"));// name属性值应当为coco
    }
    @Test
    public void testSaveGame() throws Exception {
        String url = "/game/saveGame";
        Game game = new Game.Builder()
                .withId(2)
                .withName("darcy")
                .withCityList(Arrays.asList(new City.Builder()
                        .withId(100)
                        .withCityName("wuXi-china")
                        .builder()))
                .build();
        String content = JSON.toJSONString(game);
        Assert.assertNotNull(content, "Request must not be null");
        /**
         * TODO
         * 请求为json时，会报错Type = org.springframework.web.HttpMediaTypeNotSupportedException。
         * 根据调用栈追踪猜测是缺少jackson-dataformat-xml的jar，导致
         * controller中方法的入参注解 @RequestBoy和@validate报错
         * 因为要下班了，这里就不在深究了
         */

        // actual test code
        this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(content)
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


    @Test
    public void test() {
        //创建mock对象，参数可以是类，也可以是接口
        List<String> list = mock(List.class);

        //设置方法的预期返回值
        when(list.get(0)).thenReturn("helloworld");

        String result = list.get(0);

        //junit测试
        Assert.assertEquals("helloworld", result);

        List<String> list2 = mock(List.class);

        when(list2.get(anyInt())).thenReturn("hello", "world");
    }
}
