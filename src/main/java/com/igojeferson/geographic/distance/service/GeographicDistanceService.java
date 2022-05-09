package com.igojeferson.geographic.distance.service;

import com.igojeferson.geographic.distance.dto.GeographicDistanceDto;
import com.igojeferson.geographic.distance.dto.LatLng;
import com.igojeferson.geographic.distance.dto.PostcodeDto;
import com.igojeferson.geographic.distance.exception.ServiceValidationException;
import com.igojeferson.geographic.distance.utils.LatLngConverter;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeographicDistanceService {

    private static final Logger logger = LoggerFactory.getLogger(GeographicDistanceService.class);

    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    private PostcodeService postcodeService;

    public GeographicDistanceService(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    public GeographicDistanceDto calculate(String origin, String destination){
        if (StringUtils.isBlank(origin) | StringUtils.isBlank(destination)){
            throw new ServiceValidationException("Both postal codes must be informed");
        }

        logger.info("Calculating distance between {} and {}", origin, destination);
        // TODO: validate ceps

        PostcodeDto postcode1 = postcodeService.findByPostcode(origin);
        PostcodeDto postcode2 = postcodeService.findByPostcode(destination);

        double distance = this.calculateDistance(Double.valueOf(postcode1.getLatitude()), Double.valueOf(postcode1.getLongitude()),
                Double.valueOf(postcode2.getLatitude()), Double.valueOf(postcode2.getLongitude()));

        GeographicDistanceDto distanceDto = new GeographicDistanceDto();

        // convert the origin postcode to degrees
        LatLng latLngOrigin = LatLngConverter.processCoordinates(postcode1.getLatitude(), postcode1.getLongitude());
        distanceDto.setOrigin(new PostcodeDto(
                postcode1.getId(),
                postcode1.getPostcode(),
                latLngOrigin.getLatitude(),
                latLngOrigin.getLongitude()
        ));

        // convert the destination postcode to degrees
        LatLng latLngDestination = LatLngConverter.processCoordinates(postcode2.getLatitude(), postcode2.getLongitude());
        distanceDto.setDestination(new PostcodeDto(
                postcode2.getId(),
                postcode2.getPostcode(),
                latLngDestination.getLatitude(),
                latLngDestination.getLongitude()
        ));

        distanceDto.setDistance(distance);

        return distanceDto;
    }

    private static double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
        // Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }

    private static double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(double x) {
        return x * x;
    }

}
