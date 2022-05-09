package com.igojeferson.geographic.distance.dto;

import lombok.Data;

@Data
public class GeographicDistanceDto {

    private PostcodeDto origin;
    private PostcodeDto destination;
    private double distance;
    private String unit = "km";
}
