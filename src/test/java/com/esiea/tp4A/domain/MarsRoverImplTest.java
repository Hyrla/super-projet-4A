package com.esiea.tp4A.domain;

import com.esiea.tp4A.GameMap;
import com.esiea.tp4A.MarsRoverImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class MarsRoverImplTest {

    @Test
    void createMarsRoverWithPosition(){
        GameMap gameMap = new GameMap(null);
        MarsRoverImpl rover = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH), gameMap, 1);
    }

    @Test
    void createMarsRoverWithXY() {
        GameMap gameMap = new GameMap(null);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 1);
    }

    @Test
    void move_forward() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("f");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void move_backward() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("b");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void rotate_left() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("l");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void rotate_right() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("r");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void move_up_right() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("frfl");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(1, 1, Direction.NORTH));
    }

    @Test
    void move_2_circles() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("frFRfrFrblBLbLbl");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_to_obstacle_neg2_0() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("lff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(-1, 0, Direction.WEST));
    }

    @Test
    void move_to_obstacle_2_2() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("ffrff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(1, 2, Direction.EAST));
    }

    @Test
    void laserShootNorth() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Assertions.assertThat(gameMap.isPositionFree(2,2))
            .as("Obstacle [2,2] is not yet destroyed")
            .isEqualTo(false);

        rover.move("rffls");

        Assertions.assertThat(gameMap.isPositionFree(2,2))
            .as("Obstacle [2,2] is destroyed after rffls")
            .isEqualTo(true);
    }

    @Test
    void laserShootNorthAndMove() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Assertions.assertThat(gameMap.isPositionFree(2,2))
            .as("Obstacle [2,2] is not yet destroyed")
            .isEqualTo(false);

        Position newPosition = rover.move("rfflsff");

        Assertions.assertThat(gameMap.isPositionFree(2,2))
            .as("Obstacle [2,2] is destroyed after rffls")
            .isEqualTo(true);
        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(2, 2, Direction.NORTH));
    }

    @Test
    void laserShootWest(){
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-1, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.WEST, gameMap, 2);

        rover.move("s");

        Assertions.assertThat(gameMap.isPositionFree(-1,0))
            .as("Obstacle [-1,0] is destroyed after s")
            .isEqualTo(true);
    }

    @Test
    void laserShootSouth(){
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(0, -1, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.SOUTH, gameMap, 2);

        rover.move("s");

        Assertions.assertThat(gameMap.isPositionFree(0,-1))
            .as("Obstacle [0,-1] is destroyed after s")
            .isEqualTo(true);
    }

    @Test
    void laserShootEast(){
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(1, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.EAST, gameMap, 2);

        rover.move("s");

        Assertions.assertThat(gameMap.isPositionFree(1,0))
            .as("Obstacle [1,0] is destroyed after s")
            .isEqualTo(true);
    }

    @Test
    void roverMoveFrontSurrounded(){
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(1, 0, Direction.NORTH),
            Position.of(-1, 0, Direction.NORTH),
            Position.of(0, 1, Direction.NORTH),
            Position.of(0, -1, Direction.NORTH)
            )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 1);

        Position newPosition = rover.move("frfrfrfr");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"frfrfrfr\" command but surrounded")
            .isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void roverMoveBackSurrounded(){
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(1, 0, Direction.NORTH),
            Position.of(-1, 0, Direction.NORTH),
            Position.of(0, 1, Direction.NORTH),
            Position.of(0, -1, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 1);

        Position newPosition = rover.move("brbrbrbr");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"brbrbrbr\" command but surrounded")
            .isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void unknownCommand1() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("l;");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void unknownCommand2() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 3, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("fqf");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 2, Direction.NORTH));
    }

    @Test
    void unknownCommand3() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("f r");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 1, Direction.EAST));
    }

    @Test
    void unknownCommand4() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )));
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("b+b");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, -2, Direction.NORTH));
    }
}

