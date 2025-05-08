package com.vivekemipre.dynamicpricing.util;

import com.vivekemipre.dynamicpricing.enums.Region;

public class GeoRegionBox {

    private double startLat,startLon,endLat,endLon;
    private Region region;

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public GeoRegionBox(double startLat, double startLon, double endLat, double endLon, Region region) {
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.region = region;
    }

    boolean contains(double lat, double lon){
        return (lat >= startLat && lat <= endLat) && (lon >= startLon && lon <= endLon);
    }

}
