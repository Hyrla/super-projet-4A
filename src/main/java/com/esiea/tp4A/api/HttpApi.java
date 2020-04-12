package com.esiea.tp4A.api;

import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;
import com.sun.net.httpserver.*;
import org.json.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;


public class HttpApi implements Api{

    private static HashMap<String, MarsRover> players;

    public static void main(String[] args) throws IOException {
        players = new HashMap<>();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(HttpApi::handleRequest);
        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();

        String path = requestURI.getPath();
        String returnValue = "Incorrect value";
        Integer HttpReturnCode = 404;

        if (exchange.getRequestMethod().equals("GET")){
            String[] segments = path.split("/");
            String playername = segments[3];

            if (path.startsWith("/api/player/") && !playername.isEmpty()){
                returnValue = "Get player " + playername;
                HttpReturnCode = 200;
            }
        }
        else if(exchange.getRequestMethod().equals("POST")){
            String[] segments = path.split("/");
            String playername = segments[3];

            if (path.startsWith("/api/player/") && !playername.isEmpty()){
                returnValue = "Post player " + playername;
                HttpReturnCode = 201;
            }
        }
        else if (exchange.getRequestMethod().equals("PATCH")){
            String[] segments = path.split("/");
            String playername = segments[3];
            String command = segments[4];
            System.out.print(playername + " " + command);

            if (path.startsWith("/api/player/") && !playername.isEmpty() && !command.isEmpty()){
                returnValue = "Patch command " + command +
                    " to player " + playername;
                HttpReturnCode = 201;
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

/*    private void getPlayer(String playername){
        MarsRover player = players.get(playername);
        JSONObject json = new JSONObject(player);

        System.out.print(json);
    }

    private void setPlayer(JSONObject player){

    }*/
}
