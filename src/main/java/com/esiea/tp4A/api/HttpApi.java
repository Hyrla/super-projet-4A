package com.esiea.tp4A.api;

import com.esiea.tp4A.GameMap;
import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import com.sun.source.tree.NewArrayTree;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class HttpApi implements Api{
    private static HashMap<String, MarsRover> players; // These 2 variables cannot be final because we have to edit them in the class
    private static GameMap gameMap;

    public static void main(String[] args) throws IOException {
        HttpApi api = new HttpApi();
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

    public HttpApi() throws IOException {
        players = new HashMap<>();
        gameMap = new GameMap(new HashSet<>(generateRandomThings(10, 50)), 100);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(this::handleRequest);
        server.start();
    }

    private void handleRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();

        String path = requestURI.getPath();
        String returnValue = "Incorrect value";
        int HttpReturnCode = 404;

        if (exchange.getRequestMethod().equals("GET")) {
            String[] segments = path.split("/");
            String playername = segments[3];

            if (path.startsWith("/api/player/") && !playername.isEmpty()) {
                if (players.containsKey(playername)) {
                    returnValue = getMarsRoverJsonData(players.get(playername));
                    HttpReturnCode = 200;
                } else {
                    returnValue = "Player " + playername + " don't exist !";
                    HttpReturnCode = 404;
                }
            }
        }

        else if(exchange.getRequestMethod().equals("POST")){
            String[] segments = path.split("/");
            String playername = segments[3];

            if (path.startsWith("/api/player/") && !playername.isEmpty()) {
                if (players.containsKey(playername)) {
                    returnValue = "Player " + playername + " already used !";
                    HttpReturnCode = 409;
                } else {
                    returnValue = getMarsRoverJsonData(
                        createMarsRoverFromName(playername, gameMap, 1));
                    HttpReturnCode = 201;
                }
            }
        }

        else if (exchange.getRequestMethod().equals("PATCH")){
            String[] segments = path.split("/");
                String playername = segments[3];
                String command = segments[4];

                if (path.startsWith("/api/player/") && !playername.isEmpty() && !command.isEmpty()) {
                    if ((command.equals("f")
                        || command.equals("b")
                        || command.equals("r")
                        || command.equals("l")
                        || command.equals("s"))
                    && players.containsKey(playername)){
                        players.get(playername).move(command);
                        returnValue = "Player " + playername + " done command " + command;
                    }
                    else{
                        returnValue = "Incorrect command !";
                        HttpReturnCode = 404;
                    }

                }
            }


        exchange.sendResponseHeaders(HttpReturnCode, returnValue.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(returnValue.getBytes());
        os.close();
    }

    @Override
    public Position getPosition(MarsRover rover) {
        return null;
    }

    @Override
    public ArrayList<Position> getRadarData(MarsRover rover, int range) {
        return null;
    }

    @Override
    public int getLaserRange(MarsRover rover) {
        return 0;
    }

    @Override
    public Position move(MarsRover rover, String command) {
        return null;
    }

    @Override
    public void laserShoot(MarsRover rover) {

    }

    @Override
    public boolean isPilotAlive(MarsRover rover) {
        return false;
    }

    private String getMarsRoverJsonData(MarsRover rover) {
        Gson gson = new Gson();
        return gson.toJson(rover);
    }

    private MarsRover createMarsRoverFromName(String name, GameMap gm, int laserRange) {
        int max = 50;
        int min = -50;
        int x,y = 0;

        do {
            x = (int)(Math.random()*((max-min)+1))+min;
            y = (int)(Math.random()*((max-min)+1))+min;
        } while (gameMap.isPositionFree(x,y));


        MarsRoverImpl mr = new MarsRoverImpl(x, y, Direction.NORTH, gameMap, laserRange);
        players.put(name, mr);
        return mr;
    }
}
