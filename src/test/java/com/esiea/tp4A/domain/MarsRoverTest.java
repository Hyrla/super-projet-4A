package com.esiea.tp4A.domain;

import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.GameMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

class MarsRoverTest {
    private final GameMap gameMap = new GameMap(new HashSet<>(Arrays.asList(Position.of(2, 2, Direction.NORTH), Position.of(-2, 0, Direction.NORTH))));

    private final MarsRover rover = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH), gameMap);

    @Test
    void move_forward() {
        Position newPosition = rover.move("f");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void move_backward() {
        Position newPosition = rover.move("b");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void rotate_left() {
        Position newPosition = rover.move("l");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void rotate_right() {
        Position newPosition = rover.move("r");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @Test
    void move_up_right() {
        Position newPosition = rover.move("frfl");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(1, 1, Direction.NORTH));
    }

    @Test
    void move_2_circles() {
        Position newPosition = rover.move("frFRfrFrblBLbLbl");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_to_obstacle_neg2_0() {
        Position newPosition = rover.move("lff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(-1, 0, Direction.WEST));
    }

    @Test
    void move_to_obstacle_2_2() {
        Position newPosition = rover.move("ffrff");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(1, 2, Direction.EAST));
    }
}
