package com.user.servlet.service;

import com.user.servlet.dao.FlightDao;
import com.user.servlet.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.*;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private FlightService() {
    }

    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {
        return flightDao.findAll()
                .stream()
                .map(flight -> FlightDto.builder()
                        .id(flight.getId())
                        .description(
                                """
                                        %s - %s - %s
                                        """.formatted(flight.getDepartureAirportCode(), flight.getArrivalAirportCode(), flight.getStatus()))
                        .build())
                .collect(toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }

}
