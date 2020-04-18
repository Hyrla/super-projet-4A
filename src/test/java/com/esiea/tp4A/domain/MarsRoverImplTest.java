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
        GameMap gameMap = new GameMap(100);
        MarsRoverImpl rover = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH), gameMap, 1);
    }

    @Test
    void createMarsRoverWithXY() {
        GameMap gameMap = new GameMap(100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 1);
    }

    @Test
    void move_forward() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
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
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("b");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"b\" command")

            .isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void rotate_left() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("l");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"l\" command")

            .isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void rotate_right() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("r");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"r\" command")

            .isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void move_up_right() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("frfl");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"frfl\" command")

            .isEqualTo(Position.of(1, 1, Direction.NORTH));
    }

    @Test
    void move_2_circles() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("frFRfrFrblBLbLbl");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"frFRfrFrblBLbLbl\" command")

            .isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_to_obstacle_neg2_0() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("lff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"lff\" command")

            .isEqualTo(Position.of(-1, 0, Direction.WEST));
    }

    @Test
    void move_to_obstacle_2_2() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("ffrff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"ffrff\" command")

            .isEqualTo(Position.of(1, 2, Direction.EAST));
    }

    @Test
    void laserShootNorth() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )),100);
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
        )), 100);
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
        )), 100);
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
        )), 100);
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
        )), 100);
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
        )), 100);
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
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 1);

        Position newPosition = rover.move("brbrbrbr");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"brbrbrbr\" command but surrounded")
            .isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void rover_move_from_different_origin() {
        GameMap gameMap = new GameMap(100);
        MarsRoverImpl rover = new MarsRoverImpl(10, 20, Direction.WEST, gameMap, 2);

        Position newPosition = rover.move("fflblb");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"fflblf\" command while started at 10, 20, WEST")
            .isEqualTo(Position.of(7, 21, Direction.EAST));
    }

    @Test
    void unknown_command() {
        GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(
            Position.of(2, 2, Direction.NORTH),
            Position.of(-2, 0, Direction.NORTH)
        )), 100);
        MarsRoverImpl rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);
        Position newPosition = rover.move("l;");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"l;\" command")
            .isEqualTo(Position.of(0, 0, Direction.WEST));

        rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);
        newPosition = rover.move("fqf");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"fqf\" command")
            .isEqualTo(Position.of(0, 2, Direction.NORTH));

        rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);
        newPosition = rover.move("f r");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"f r\" command")
            .isEqualTo(Position.of(0, 1, Direction.EAST));

        rover = new MarsRoverImpl(0, 0, Direction.NORTH, gameMap, 2);
        newPosition = rover.move("b+b");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"b+b\" command")
            .isEqualTo(Position.of(0, -2, Direction.NORTH));
    }

    @Test
    void rover_move_above_map_size_limit() {
        GameMap gameMap = new GameMap(100);
        MarsRoverImpl rover = new MarsRoverImpl(48, 48, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("fff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"fff\" command while started at 48, 48 NORTH")
            .isEqualTo(Position.of(48, -49, Direction.NORTH));

        newPosition = rover.move("rfff");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"rfff\" command while started at 48, -49 NORTH")
            .isEqualTo(Position.of(-49, -49, Direction.EAST));

        newPosition = rover.move("rfff");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"rfff\" command while started at -49, -49 EAST")
            .isEqualTo(Position.of(-49, 48, Direction.SOUTH));
    }

    @Test
    void create_rover_at_out_of_map_position() {
        GameMap gameMap = new GameMap(100);
        MarsRoverImpl rover = new MarsRoverImpl(112, -52, Direction.NORTH, gameMap, 2);

        Position newPosition = rover.move("");

        Assertions.assertThat(newPosition)
            .as("Rover position after creation")
            .isEqualTo(Position.of(12, 48, Direction.NORTH));
    }

    @Test
    void complex_moves_from_different_origin() {
        GameMap gameMap = new GameMap(100);

        MarsRoverImpl rover = new MarsRoverImpl(5, -3, Direction.WEST, gameMap, 2);
        Position newPosition = rover.move("ff");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"ff\" command while started at 48, 48 WEST")
            .isEqualTo(Position.of(3, -3, Direction.WEST));

        rover = new MarsRoverImpl(5, -3, Direction.WEST, gameMap, 2);
        newPosition = rover.move("lbblffr");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"lbblffr\" command while started at 48, 48 WEST")
            .isEqualTo(Position.of(7, -1, Direction.SOUTH));

        rover = new MarsRoverImpl(500, -300, Direction.NORTH, gameMap, 2);
        newPosition = rover.move("flf");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"flf\" command while started at 500, -300 (0, 0) NORTH")
            .isEqualTo(Position.of(-1, 1, Direction.WEST));
    }


    @Test
    void unknown_command_should_be_ignored() {
        GameMap gameMap = new GameMap(100);

        MarsRoverImpl rover = new MarsRoverImpl(-2, 1, Direction.SOUTH, gameMap, 2);
        Position newPosition = rover.move("aff");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"aff\" command while started at -2, 1 SOUTH")
            .isEqualTo(Position.of(-2, -1, Direction.SOUTH));

        rover = new MarsRoverImpl(-2, 1, Direction.SOUTH, gameMap, 2);
        newPosition = rover.move("f f");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"f f\" command while started at -2, 1 SOUTH")
            .isEqualTo(Position.of(-2, -1, Direction.SOUTH));

        rover = new MarsRoverImpl(-2, 1, Direction.SOUTH, gameMap, 2);
        newPosition = rover.move("f,f");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"f,f\" command while started at -2, 1 SOUTH")
            .isEqualTo(Position.of(-2, -1, Direction.SOUTH));

        rover = new MarsRoverImpl(-2, 1, Direction.SOUTH, gameMap, 2);
        newPosition = rover.move("ff;");
        Assertions.assertThat(newPosition)
            .as("Rover position after \"ff;\" command while started at -2, 1 SOUTH")
            .isEqualTo(Position.of(-2, -1, Direction.SOUTH));
    }
}

