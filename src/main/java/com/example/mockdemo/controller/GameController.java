package com.example.mockdemo.controller;

import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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

    @PutMapping(value="/updateGame", consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateGame(@Validated(Game.Existing.class) @RequestBody Game game) {
        gameService.updateGame(game);
    }

    @PostMapping(value="/saveGame", consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveGame(@Validated(Game.New.class) @RequestBody Game game) {
        gameService.updateGame(game);
    }
}
