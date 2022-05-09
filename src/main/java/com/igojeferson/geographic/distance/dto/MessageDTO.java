package com.igojeferson.geographic.distance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MessageDTO {

    private Integer statusCode;
    private String message;
}
