package com.igojeferson.geographic.distance.controller;

import com.igojeferson.geographic.distance.dto.GeographicDistanceDto;
import com.igojeferson.geographic.distance.service.GeographicDistanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/geographic-distance")
public class GeographicDistanceController {

    private GeographicDistanceService geographicDistanceService;

    public GeographicDistanceController(GeographicDistanceService geographicDistanceService) {
        this.geographicDistanceService = geographicDistanceService;
    }

//     ([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\s?[0-9][A-Za-z]{2})
    @GetMapping
//    @GetMapping("/origin/{origin}/destination/{destination}")
    public ResponseEntity<GeographicDistanceDto> calculateDistance(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination) {
        return ResponseEntity.ok(geographicDistanceService.calculate(origin, destination));
    }
}