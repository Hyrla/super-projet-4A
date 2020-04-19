package com.esiea.tp4A.api;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.boot.SpringApplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;

public class HttpApiTest {

    @Test
    void testWebserver() {
        SpringApplication.run(RestAPI.class);
        try {
            URL url = new URL("http://localhost:8080/api/players");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        try {
            URL url = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        try {
            URL url = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Assertions.assertThat(con.getResponseCode() == 200);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }

        try {
            URL url = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            URL url2 = new URL("http://localhost:8080/api/player/joueur");
            HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
            con2.setRequestMethod("POST");
            Assertions.assertThat(con.getResponseCode() == 409);
        } catch (Exception e) {
            Assertions.assertThat(false);
        }
    }
}
