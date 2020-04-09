package com.esiea.tp4A.domain;

import com.esiea.tp4A.MarsRoverImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverTest {
    private final MarsRover rover = new MarsRoverImpl(Position.of(0, 0, Direction.NORTH));

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
}
