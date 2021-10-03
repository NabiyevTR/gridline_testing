package com.gridnine.testing.flight_filter;

import com.gridnine.testing.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> getFlights();

    FlightFilterImpl departureBeforeNow();

    FlightFilterImpl containsSegmentsWithArrivalsBeforeDeparture();

    FlightFilterImpl timeOnLandMoreThan(int hours);

    FlightFilterImpl timeOnLandMoreThanTwoHours();
}
