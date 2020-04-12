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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpApi {

    private static HashMap<Object, MarsRover> players;

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
            String playername = path.substring(path.lastIndexOf('/') + 1);
            if (path.startsWith("/api/player/") && !playername.isEmpty()){
                returnValue = "Get player " + path.substring(path.lastIndexOf('/') + 1);
                HttpReturnCode = 200;
            }
        }
        else if(exchange.getRequestMethod().equals("POST")){
            String playername = path.substring(path.lastIndexOf('/') + 1);
            if (path.startsWith("/api/player/") && !playername.isEmpty()){
                returnValue = "Post player " + path.substring(path.lastIndexOf('/') + 1);
                HttpReturnCode = 201;
            }
        }

        exchange.sendResponseHeaders(HttpReturnCode, returnValue.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(returnValue.getBytes());
        os.close();
    }

/*    private void getPlayer(String playername){
        MarsRover player = players.get(playername);
        JSONObject json = new JSONObject(player);

        System.out.print(json);
    }

    private void setPlayer(JSONObject player){

    }*/
}
