package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;

public class MarsRoverImpl implements MarsRover {
    @Override
    public Position move(String command) {
        return Position.of(0, 1, Direction.NORTH);
    }
}
