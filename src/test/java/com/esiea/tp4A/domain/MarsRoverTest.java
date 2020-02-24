package com.esiea.tp4A.domain;

import com.esiea.tp4A.MarsRoverImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverTest {
    private final MarsRover rover = new MarsRoverImpl()
        .initialize(Position.of(0, 0, Direction.NORTH));

    @Test
    void move_forward() {
        Position newPosition = rover.move("f");

        Assertions.assertThat(newPosition)
            .as("Rover position after \"f\" command")
            .isEqualTo(Position.of(0, 1, Direction.NORTH));
    }
}
