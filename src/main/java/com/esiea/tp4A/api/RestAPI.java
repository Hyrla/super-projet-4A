package com.esiea.tp4A.api;

import com.esiea.tp4A.GameMap;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@SpringBootApplication
public class RestAPI extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RestAPI.class, args);
    }
}
