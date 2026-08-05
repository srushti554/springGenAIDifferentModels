package org.example.service.impl;

import org.example.service.FareCalculatorService;

public class BasicFareCalculatorServiceImpl implements FareCalculatorService {

    private int baseDistance;
    private double baseFare;
    private double beyondBaseFare;

    public BasicFareCalculatorServiceImpl(int baseDistance, double baseFare, double beyondBaseFare) {
        this.baseDistance = baseDistance;
        this.baseFare = baseFare;
        this.beyondBaseFare = beyondBaseFare;
    }

    @Override
    public double calculateFarePerPerson(int distance) {
        if(distance<=baseDistance) {
            return baseFare;
        }
        int beyondBaseDistance = distance - baseDistance;
        return baseFare + (beyondBaseDistance * beyondBaseFare);
    }
}
