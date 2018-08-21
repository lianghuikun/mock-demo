package com.example.mockdemo.service.impl;

import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Game getGameById(Integer id) {
        return new Game.Builder()
                .withId(1)
                .withName("name")
                .build();
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void saveGame(Game game) {

    }

    @Override
    public List<Game> getGameList() {

        List<Game> gameList = Arrays.asList(new Game.Builder()
                .withId(1)
                .withName("name")
                .build());

        return gameList;
    }
}
