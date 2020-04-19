package com.esiea.tp4A.api;

import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;

import java.util.ArrayList;

public interface Api {
    Position getPosition(MarsRover rover);
    ArrayList<Position> getRadarData(MarsRover rover, int range);
    int getLaserRange(MarsRoverImpl rover); // Impl because laser
    Position move(MarsRover rover, String command);
    void laserShoot(MarsRoverImpl rover); // Impl because laser
    boolean isPilotAlive(MarsRover rover);
}
