package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;

public class MarsRoverImpl implements MarsRover {
    private Position position;
    private GameMap gameMap;
    private int laserRange;

    public MarsRoverImpl(int x, int y, Direction direction, GameMap gameMap, int laserRange) {
        this.position = new Position.FixedPosition(x, y ,direction);
        this.laserRange = laserRange;
        this.gameMap = gameMap;
    }
    public MarsRoverImpl(Position position, GameMap gameMap, int laserRange) {
        this.position = position;
        this.gameMap = gameMap;
        this.laserRange = laserRange;
    }

    @Override
    public Position move(String command) {
        for (char cmd : command.toLowerCase().toCharArray()) {
            Position newPosition = this.position;
            switch (cmd) {
                case 'f':
                    newPosition = moveForward();
                    break;
                case 'b':
                    newPosition = moveBackward();
                    break;
                case 'l':
                    newPosition = rotateLeft();
                    break;
                case 'r' :
                    newPosition = rotateRight();
                    break;
                case 's':
                    laserShoot();
            }
            this.position = newPosition;
        }
        return position;
    }

    private Position moveForward() {
        int newX = this.position.getX();
        int newY = this.position.getY();

        switch (position.getDirection()) {
            case NORTH:
                if(gameMap.isPositionFree(newX, newY+1))
                {
                    newY++;
                }
                break;
            case EAST:
                if(gameMap.isPositionFree(newX+1, newY))
                {
                    newX++;
                }
                break;
            case SOUTH:
                if(gameMap.isPositionFree(newX, newY-1))
                {
                    newY--;
                }
                break;
            case WEST:
                if(gameMap.isPositionFree(newX-1, newY))
                {
                    newX--;
                }
                break;
        }
        return Position.of(newX, newY, this.position.getDirection());
    }

    private Position moveBackward() {
        int newX = this.position.getX();
        int newY = this.position.getY();

        switch (position.getDirection()) {
            case NORTH:
                if(gameMap.isPositionFree(newX, newY-1))
                {
                    newY--;
                }
                break;
            case EAST:
                if(gameMap.isPositionFree(newX-1, newY))
                {
                    newX--;
                }
                break;
            case SOUTH:
                if(gameMap.isPositionFree(newX, newY+1))
                {
                    newY++;
                }
                break;
            case WEST:
                if(gameMap.isPositionFree(newX+1, newY))
                {
                    newX++;
                }
                break;
        }
        return Position.of(newX, newY, this.position.getDirection());
    }

    private Position rotateLeft() {
        Direction newDirection = this.position.getDirection();

        switch (newDirection) {
            case NORTH:
                newDirection = Direction.WEST;
                break;
            case EAST:
                newDirection = Direction.NORTH;
                break;
            case SOUTH:
                newDirection = Direction.EAST;
                break;
            case WEST:
                newDirection = Direction.SOUTH;
                break;
        }

        return Position.of(this.position.getX(), this.position.getY(), newDirection);
    }

    private Position rotateRight() {
        Direction newDirection = this.position.getDirection();

        switch (newDirection) {
            case NORTH:
                newDirection = Direction.EAST;
                break;
            case EAST:
                newDirection = Direction.SOUTH;
                break;
            case SOUTH:
                newDirection = Direction.WEST;
                break;
            case WEST:
                newDirection = Direction.NORTH;
                break;
        }

        return Position.of(this.position.getX(), this.position.getY(), newDirection);
    }

    public void laserShoot() {
        int positionX = position.getX();
        int positionY = position.getY();

        for (int i = 1; i <= laserRange; i++) {

            switch (position.getDirection()) {
                case NORTH:
                    if(! gameMap.isPositionFree(positionX, positionY+i))
                    {
                        gameMap.destroyObstacle(Position.of(positionX, positionY+i,Direction.NORTH));
                    }
                    break;
                case EAST:
                    if(! gameMap.isPositionFree(positionX+i, positionY))
                    {
                        gameMap.destroyObstacle(Position.of(positionX+i, positionY,Direction.NORTH));
                    }
                    break;
                case SOUTH:
                    if(! gameMap.isPositionFree(positionX, positionY-i))
                    {
                        gameMap.destroyObstacle(Position.of(positionX, positionY-i,Direction.NORTH));
                    }
                    break;
                case WEST:
                    if(! gameMap.isPositionFree(positionX-i, positionY))
                    {
                        gameMap.destroyObstacle(Position.of(positionX-i, positionY,Direction.NORTH));
                    }
                    break;
            }

        }

    }
}
