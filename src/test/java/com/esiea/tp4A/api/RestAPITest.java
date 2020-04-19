package com.esiea.tp4A.api;

import com.esiea.tp4A.GameMap;
import com.esiea.tp4A.MarsRoverImpl;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.boot.SpringApplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;

public class RestAPITest {

    @Test
    void mainTest() {
        String[] args = {"DEBUG"};
        RestAPI.main(args);
        try {
            URL url = new URL("http://localhost:8080/api/players");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }
    }
}
