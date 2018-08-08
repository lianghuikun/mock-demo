package com.example.mockdemo.controller;

import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lianghuikun
 * @Description: TODO
 * @Date: 2018/8/8/008 21:17
 */
@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/getGameById")
    public Game getGameById(Integer id) {
        return gameService.getGameById(id);
    }
}
