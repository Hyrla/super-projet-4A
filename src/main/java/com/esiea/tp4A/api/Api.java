package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;

import java.util.ArrayList;

public interface Api {
    // Refactoring in progress
    Position getPosition(MarsRover rover);
    ArrayList<Position> getRadarData(MarsRover rover, int range);
    int getLaserRange(MarsRover rover);
    Position move(MarsRover rover, String command);
    void laserShoot(MarsRover rover);
    boolean isPilotAlive(MarsRover rover);
}
