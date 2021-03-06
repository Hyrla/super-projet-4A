package com.esiea.tp4A.api;

import com.esiea.tp4A.GameMap;
import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;

public class HttpApiTest {

    @Test
    void testImplApi() {
        HttpApi api = new HttpApi();
        MarsRoverImpl m = new MarsRoverImpl(new Position.FixedPosition(0, 0, Direction.NORTH), new GameMap(100), 1);
        Assertions.assertThat(api.getPosition(m).equals(new Position.FixedPosition(0, 0, Direction.NORTH)));
        Assertions.assertThat(api.getRadarData(m, 0) == null);
        Assertions.assertThat(api.getLaserRange(m) == 1);
        Assertions.assertThat(api.move(m, "f").equals(new Position.FixedPosition(0, 1, Direction.NORTH)));
        api.laserShoot(m);
        Assertions.assertThat(api.getLaserRange(m) == 1);
        Assertions.assertThat(api.isPilotAlive(m));

    }

    @Test
    void testWebserver() {
        ConfigurableApplicationContext context = SpringApplication.run(RestAPI.class);
        // Trying to get all players
        try {
            URL url = new URL("http://localhost:8080/api/players");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        // Trying to create a player
        try {
            URL url = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        // Trying to get an existing player
        try {
            URL url = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        // Trying to get a non existant player
        try {
            URL url = new URL("http://localhost:8080/api/player/joueurpasla");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Assertions.assertThat(con.getResponseCode() == 404);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        // Trying to move an existing player
        try {
            URL url = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PATCH");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        // Trying to move a non existing player
        try {
            URL url = new URL("http://localhost:8080/api/player/joueurpasla");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PATCH");
            Assertions.assertThat(con.getResponseCode() == 404);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        // Trying to create 2 players with same name
        try {
            URL url = new URL("http://localhost:8080/api/player/joueur2");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            URL url2 = new URL("http://localhost:8080/api/player/joueur2");
            HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
            con2.setRequestMethod("POST");
            Assertions.assertThat(con2.getResponseCode() == 409);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }
        context.close();
    }
}
