package com.esiea.tp4A.domain;

import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.GameMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverTest {
    @Test
    public void initializeTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new GameMap(100),1);
        Assertions.assertThat(marsRover.initialize(Position.of(0,0, Direction.NORTH))).isEqualTo(marsRover);
    }

    @Test
    public void moveTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new GameMap(100),1);
        Assertions.assertThat(marsRover.move("x")).isEqualTo(Position.of(0,0, Direction.NORTH));
    }

    @Test
    public void updateMapTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new GameMap(100),1);
        Assertions.assertThat(marsRover.updateMap(new GameMap(100))).isEqualTo(marsRover);
    }

    @Test
    public void configureLaserRangeTest(){
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new GameMap(100),30);
        Assertions.assertThat(marsRover.configureLaserRange(30)).isEqualTo(marsRover);
    }

    @Test
    void initialMove() {
        MarsRover marsRover = new MarsRoverImpl(0,0, Direction.NORTH, new GameMap(100),30);
        Assertions.assertThat(marsRover.move("f").equals(new Position.FixedPosition(0, 1, Direction.NORTH)));
    }
}
