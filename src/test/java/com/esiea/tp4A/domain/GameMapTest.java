package com.esiea.tp4A.domain;

import com.esiea.tp4A.GameMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class GameMapTest {

    @Test
    void emptyGameMap(){
        GameMap gameMap = new GameMap(100);
    }

    @Test
    void getSize(){
        GameMap gameMap = new GameMap(100);
        Assertions.assertThat(gameMap.getSize())
            .as("size of map")
            .isEqualTo(100);
    }

    @Test
    void addObstacle(){
        GameMap gameMap = new GameMap(100);

        Assertions.assertThat(gameMap.isPositionFree(1,1));
        gameMap.addObstacle(Position.of(1,1,Direction.NORTH));
        Assertions.assertThat(!gameMap.isPositionFree(1,1));
    }

    @Test
    void getObstacles(){
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH)
        )), 100);

        Assertions.assertThat(gameMap.obstaclePositions()).isEqualTo(
            new HashSet<>(Arrays.asList(Position.of(2, 2, Direction.NORTH)))
        );
    }
}
