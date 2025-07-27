package com.vivekemipre.dynamicpricing.util;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Component
public class DynamicPricingCalculator {

    private final double peakTimeMultiplier=1.1002;
    private final double k = 0.100005; // sensitivity to demand
    private final double maxMultiplier = 1.25;
    private final double ifEatingTimeMultiplier=1.10002;
    private final double ifNotEatingTimeMultiplier=0.98;

    private String categorizeTiming(LocalDateTime dateTime) {
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

    public double getProductDynamicPrice(double productPrice, int currentDemand, boolean isPeakTime, Map<String,Boolean> eatenAt)
    {
        double dynamicPrice= isPeakTime ? productPrice*peakTimeMultiplier : productPrice;
        dynamicPrice=calculateDynamicPriceSigmoid(dynamicPrice,currentDemand);
        dynamicPrice*=eatenAt.get(categorizeTiming(LocalDateTime.now()))?ifEatingTimeMultiplier:ifNotEatingTimeMultiplier;
        return  dynamicPrice;
    }


}
