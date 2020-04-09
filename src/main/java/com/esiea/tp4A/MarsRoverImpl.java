package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;

public class MarsRoverImpl implements MarsRover {
    private Position position;

    public MarsRoverImpl(int x, int y, Direction direction) {
        this.position = new Position.FixedPosition(x, y ,direction);
    }
    public MarsRoverImpl(Position position) {
        this.position = position;
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
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case SOUTH:
                newY--;
                break;
            case WEST:
                newX--;
                break;
        }
        return Position.of(newX, newY, this.position.getDirection());
    }

    private Position moveBackward() {
        int newX = this.position.getX();
        int newY = this.position.getY();

        switch (position.getDirection()) {
            case NORTH:
                newY--;
                break;
            case EAST:
                newX--;
                break;
            case SOUTH:
                newY++;
                break;
            case WEST:
                newX++;
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
}