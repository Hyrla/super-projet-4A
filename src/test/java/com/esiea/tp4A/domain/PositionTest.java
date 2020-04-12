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
    void positionEqualWrongPostion(){
        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(!position.equals(new Position.FixedPosition(1,0,Direction.NORTH)));
        Assertions.assertThat(!position.equals(new Position.FixedPosition(0,1,Direction.NORTH)));
        Assertions.assertThat(!position.equals(new Position.FixedPosition(1,0,Direction.SOUTH)));
    }

    @Test
    void positionEqualGoodPostion(){
        Position position = new Position.FixedPosition(0,0,Direction.NORTH);

        Assertions.assertThat(position.equals(new Position.FixedPosition(0,0,Direction.NORTH)));
    }
}
