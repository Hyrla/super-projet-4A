package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.*;

public class GameMap implements PlanetMap {

    private Set<Position> obstacles;

    //Constructor with parameter, to use a default map
    public GameMap(HashSet<Position> obstacles) {
        this.obstacles = obstacles;
    }

    //Constructor without parameter to generate the map in game
    public GameMap() {
        obstacles = new HashSet<>();

    }

    public void addObstacle(Position position) {
        //An obstacle with direction has no meaning
        //To simplify the test, an obstacle can only have a NORTH direction
        obstacles.add(Position.of(position.getX(), position.getY(), Direction.NORTH));
    }

    public void destroyObstacle(Position position){
        obstacles.remove(position);
    }

    public boolean isPositionFree(int x, int y) {
        return !obstacles.contains(Position.of(x, y, Direction.NORTH));
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }
}
