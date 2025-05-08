package com.vivekemipre.dynamicpricing.util;

import com.vivekemipre.dynamicpricing.enums.Region;

import java.util.List;

public class RegionClassifier {


    private static final List<GeoRegionBox> regions = List.of(
            new GeoRegionBox(18.89, 19.00, 72.82, 72.88, Region.MUMBAI_SOUTH),
            new GeoRegionBox(19.10, 19.30, 72.88, 72.98, Region.MUMBAI_EAST),
            new GeoRegionBox(19.10, 19.30, 72.77, 72.82, Region.MUMBAI_WEST),
            new GeoRegionBox(19.20, 19.30, 72.82, 72.88, Region.MUMBAI_NORTH)
    );

    public static Region classify(double lat, double lon) {
        return regions.stream()
                .filter(r -> r.contains(lat, lon))
                .map(GeoRegionBox::getRegion)
                .findFirst()
                .orElse(Region.MUMBAI_SOUTH);
    }

}
