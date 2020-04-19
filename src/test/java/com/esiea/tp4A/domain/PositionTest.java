package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    void positionEqualItself(){
        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(position.equals(position));
    }

    @Test
    void positionEqualNull(){
        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(!position.equals(null));
    }

    @Test
    void positionEqualOtherClass(){
        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(!position.equals(1));
    }

    @Test
    void positionEqualWrongPosition(){

        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(!position.equals(new Position.FixedPosition(1,0,Direction.NORTH)));
        Assertions.assertThat(!position.equals(new Position.FixedPosition(0,1,Direction.NORTH)));
        Assertions.assertThat(!position.equals(new Position.FixedPosition(1,0,Direction.SOUTH)));
    }

    @Test
    void positionEqualGoodPosition(){

        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(position.equals(new Position.FixedPosition(0,0,Direction.NORTH)));
    }

    @Test
    void positionToString() {
        Position position = new Position.FixedPosition(0, 0, Direction.NORTH);
        Assertions.assertThat(position.toString().equals("FixedPosition{x=0, y=0, direction=NORTH}"));
    }

    @Test
    void positionEqualsAnother() {
        Position position1 = new Position.FixedPosition(0, 0, Direction.NORTH);
        Position position2 = new Position.FixedPosition(0, 0, Direction.NORTH);
        Assertions.assertThat(position1.equals(position2));
    }
}
