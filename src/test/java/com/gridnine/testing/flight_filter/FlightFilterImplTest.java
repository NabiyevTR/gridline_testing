package com.gridnine.testing.flight_filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FlightFilterImplTest {

    private List<Flight> flights;

    @BeforeEach
    public void setup() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void departureBeforeNow() {
        FlightFilter flightFilter = new FlightFilterImpl(flights);
        assertEquals(flightFilter.departureBeforeNow().getFlights().size(), 1);
    }

    @Test
    void containsSegmentsWithArrivalsBeforeDeparture() {
        FlightFilter flightFilter = new FlightFilterImpl(flights);
        assertEquals(flightFilter.containsSegmentsWithArrivalsBeforeDeparture().getFlights().size(), 1);

    }

    @Test
    void timeOnLandMoreThanTwoHours() {
        FlightFilter flightFilter = new FlightFilterImpl(flights);
        assertEquals(flightFilter.timeOnLandMoreThanTwoHours().getFlights().size(), 2);
    }
}