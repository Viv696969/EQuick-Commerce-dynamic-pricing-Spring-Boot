package com.vivekemipre.dynamicpricing.util;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DynamicPricingCalculator {

    private final double peakTimeMultiplier=1.102;
    private final double osTypeMultiplier=1.105;
    private final double k = 0.1005; // sensitivity to demand
    private final double maxMultiplier = 1.5;

    public String categorizeTiming(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();

        if (time.isAfter(LocalTime.of(4, 59)) && time.isBefore(LocalTime.of(10, 1))) {
            return "breakfast";
        } else if (time.isAfter(LocalTime.of(11, 59)) && time.isBefore(LocalTime.of(15, 1))) {
            return "lunch";
        } else if (time.isAfter(LocalTime.of(18, 59)) && time.isBefore(LocalTime.of(23, 59))) {
            return "dinner";
        } else {
            return "breakfast";
        }
    }

    private double calculateDynamicPriceSigmoid(double basePrice, int demand) {
        double multiplier = 1 + (maxMultiplier - 1) / (1 + Math.exp(-k * (demand - 10)));
        return basePrice * multiplier;
    }

    public double getProductDemandPrice(double productPrice,int currentDemand,String osType,boolean isPeakTime)
    {
        double demandedPrice= isPeakTime ? productPrice*peakTimeMultiplier : productPrice;

        demandedPrice=osType.toLowerCase().contains("mac") || osType.toLowerCase().contains("ios") ? demandedPrice*osTypeMultiplier:demandedPrice;

        return calculateDynamicPriceSigmoid(demandedPrice,currentDemand);
    }


}
