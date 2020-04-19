package com.esiea.tp4A.api;

import com.esiea.tp4A.GameMap;
import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@RestController
public class HttpApi implements Api {
    private HashMap<String, MarsRover> players = new HashMap<>();
    private GameMap gameMap = new GameMap(new HashSet<>(generateRandomThings(10, 50)), 100);

    @RequestMapping(value = "/api/player/{playername}", method = RequestMethod.GET)
    public MarsRover getRover(@PathVariable String playername) {
        MarsRover mr = players.get(playername);
        if (mr == null) { throw new PlayerNotFoundException(); } else { return mr; }
    }

    @RequestMapping(value = "/api/player/{playername}", method = RequestMethod.POST)
    public MarsRover newRover(@PathVariable String playername) {
        MarsRover newrover = new MarsRoverImpl(0,0, Direction.NORTH, new GameMap(100),1);
        players.put(playername,newrover);
        return players.get(playername);
    }

    @RequestMapping(value = "/api/player/{playername}/{command}", method = RequestMethod.PATCH)
    public Position move(@PathVariable String playername, @PathVariable String command) {
        MarsRover rover = players.get(playername);
        return rover.move(command);
    }

    private ArrayList<Position> generateRandomThings(int number, int range) {
        ArrayList<Position> a = new ArrayList<>();
        for(int i = 0; i < number; i++) {
            a.add(Position.of(
                (int)(-range + Math.random() * range * 2),
                (int)(-range + Math.random() * range * 2),
                Direction.NORTH));
        }
        return a;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "player not found")
    private class PlayerNotFoundException extends RuntimeException { }

    public Position getPosition(MarsRover rover) { return null; }
    public ArrayList<Position> getRadarData(MarsRover rover, int range) { return null; }
    public int getLaserRange(MarsRover rover) { return 0; }
    public Position move(MarsRover rover, String command) { return null; }
    public void laserShoot(MarsRover rover) { }
    public boolean isPilotAlive(MarsRover rover) { return false; }
}
