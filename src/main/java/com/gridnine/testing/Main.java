package com.gridnine.testing;

import com.gridnine.testing.flight_filter.FlightFilter;
import com.gridnine.testing.flight_filter.FlightFilterImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        //Show all flights
        System.out.println("All flights:");
        flights.forEach(System.out::println);
        System.out.println();

        //Show all flights with departure before now
        System.out.println("All flights with departure before now:");
        (new FlightFilterImpl(flights)).departureBeforeNow().getFlights().forEach(System.out::println);
        System.out.println();

        //Show all flights with segments where arrival is before departure
        System.out.println("All flights with segments where arrival is before departure:");
        (new FlightFilterImpl(flights)).containsSegmentsWithArrivalsBeforeDeparture().getFlights().forEach(System.out::println);
        System.out.println();

        //Show all flights with total time on land more than 2hrs
        System.out.println("All flights with total time on land more than 2hrs:");
        (new FlightFilterImpl(flights)).timeOnLandMoreThanTwoHours().getFlights().forEach(System.out::println);
        System.out.println();

    }
}
