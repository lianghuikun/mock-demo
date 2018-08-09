package com.example.mockdemo.service.impl;

import com.example.mockdemo.domain.Game;
import com.example.mockdemo.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Game getGameById(Integer id) {
        return null;
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void saveGame(Game game) {

    }

    @Override
    public List<Game> getGameList() {
        return null;
    }
}
