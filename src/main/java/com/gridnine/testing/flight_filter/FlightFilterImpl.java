package com.gridnine.testing.flight_filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {

    private List<Flight> flights;

    public FlightFilterImpl(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

    // Filters

    @Override
    public FlightFilterImpl departureBeforeNow() {
        flights = flights.stream()
                .filter(f -> f.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public FlightFilterImpl containsSegmentsWithArrivalsBeforeDeparture() {
        flights = flights.stream()
                .filter(f -> f.getSegments().stream()
                        .anyMatch(s -> s.getArrivalDate().isBefore(s.getDepartureDate())))
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public FlightFilterImpl timeOnLandMoreThan(int hours) {
        flights = flights.stream()
                .filter(f -> {
                    List<Segment> segments = f.getSegments();

                    if (segments.size() < 2) {
                        return false;
                    }

                    long totalMillis = 0;

                    for (int i = 1; i < segments.size(); i++) {
                        totalMillis += Duration.between(
                                segments.get(i - 1).getArrivalDate(),
                                segments.get(i).getDepartureDate()
                        ).toMillis();
                    }
                    return totalMillis > TimeUnit.HOURS.toMillis(hours);
                })
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public FlightFilterImpl timeOnLandMoreThanTwoHours() {
        return timeOnLandMoreThan(2);
    }


    // Other filters

    // ...


}
