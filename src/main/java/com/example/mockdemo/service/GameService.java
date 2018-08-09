package com.example.mockdemo.service;

import com.example.mockdemo.domain.Game;

import java.util.List;

/**
 * @Author: lianghuikun
 * @Description: TODO
 * @Date: 2018/8/8/008 21:39
 */
public interface GameService {
    /**
     * query game by id
     * @param id
     * @return
     */
    Game getGameById(Integer id);

    /**
     * update game
     * @param game
     */
    void updateGame(Game game);

    /**
     * save game
     * @param game
     */
    void saveGame(Game game);

    /**
     * get all game list
     * @return
     */
    List<Game> getGameList();
}
