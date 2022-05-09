package com.igojeferson.geographic.distance.utils;

import com.igojeferson.geographic.distance.dto.LatLng;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LatLngConverterTest {

    @Test
    void testSuccessfullConversion(){

        LatLng latLng = LatLngConverter.processCoordinates("57.169439", "-2.108973");
        assertEquals(latLng.getLatitude(), "57° 10' 9\" N");
        assertEquals(latLng.getLongitude(), "2° 6' 32\" W");


    }
}
