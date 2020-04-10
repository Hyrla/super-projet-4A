package com.esiea.tp4A.code;

import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.*;

public class GameMap implements PlanetMap {

    private Set<Position> obstacles;

    //Constructor with parameter
    public GameMap(HashSet<Position> obstacles) {
        this.obstacles = obstacles;
    }

    public GameMap() {
        obstacles = new HashSet<Position>();
    }
    public void addObstacles(Position position)
    {
        obstacles.add(position);
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }
}
