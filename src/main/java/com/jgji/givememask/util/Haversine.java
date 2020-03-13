package com.jgji.givememask.util;

public class Haversine {
    private static final double EARTH_RADIUS = 6371;

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        System.out.println("startLat >> " + startLat);
        System.out.println("startLong >> " + startLong);
        System.out.println("endLat >> " + endLat);
        System.out.println("endLong >> " + endLong);
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(startLat - endLat) * toRadian;
        double deltaLongitude = Math.abs(startLong - endLong) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
            sinDeltaLat * sinDeltaLat +
            Math.cos(startLat * toRadian) * Math.cos(endLat * toRadian) * sinDeltaLng * sinDeltaLng);

        double distance = 2 * EARTH_RADIUS * Math.asin(squareRoot);
 
        return distance ;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
