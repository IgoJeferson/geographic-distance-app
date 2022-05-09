package com.igojeferson.geographic.distance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostcodeDto {

    private int id;
    private String postcode;
    private String latitude;
    private String longitude;

}