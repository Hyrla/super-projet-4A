package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.*;

public class GameMap implements PlanetMap {

    private Set<Position> obstacles;
    private int size;

    //Constructor with parameter, to use a default map
    public GameMap(HashSet<Position> obstacles, int size) {
        this.obstacles = obstacles;
        this.size = size;
    }

    //Constructor without parameter to generate the map in game
    public GameMap(int size) {
        obstacles = new HashSet<>();
        this.size = size;
    }

    public int getSize() {
        return size;
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

    public int getRealX(int x) {
        int parity = x / (size/2);
        int realValue = x % (size/2);
        return (parity % 2 == 1) ? realValue - (size/2) : realValue;
    }

    public int getRealY(int y) {
        int parity = y / (size/2);
        int realValue = y % (size/2);
        return (parity % 2 == 1) ? realValue - (size/2) : realValue;
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }
}
