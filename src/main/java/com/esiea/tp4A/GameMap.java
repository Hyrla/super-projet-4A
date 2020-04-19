package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.*;

public class GameMap implements PlanetMap {

    public String getObstacles() {
        return obstacles.toString();
    }

    private HashSet<Position> obstacles; // This variable is not final because it has to be modified to add or remove obstacles


    private int size;

    //Constructor with parameter, to use a default map
    public GameMap(HashSet<Position> obstacles, int size) {
        this.obstacles = obstacles;
        this.size = size;
    }

    //Constructor without parameter to generate the map in game
    public GameMap(int size) {
        this.obstacles = new HashSet<>();

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
        return !obstacles.contains(Position.of(getRealX(x), getRealY(y), Direction.NORTH));
    }

    public int getRealX(int x) {
        int parity;
        int realValue;
        if (x > 0) {
            parity = ((x - 1) / (size / 2)) % 2;
            realValue = x % (size / 2);
            if (realValue == 0) { realValue = size / 2; }
        }
        else {
            parity = -1 * (((x / (size / 2)) - 1) % 2);
            realValue = (x % (-1 * (size / 2))) + 50;
        }
        return (parity == 1) ? realValue - (size / 2) : realValue;
    }

    public int getRealY(int y) {
        int parity;
        int realValue;
        if (y > 0) {
            parity = ((y - 1) / (size / 2)) % 2;
            realValue = y % (size / 2);
            if (realValue == 0) { realValue = size / 2; }
        }
        else {
            parity = -1 * (((y / (size / 2)) - 1) % 2);
            realValue = (y % (-1 * (size / 2))) + 50;
        }
        return (parity == 1) ? realValue - (size / 2) : realValue;
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }
}
