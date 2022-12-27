package com.user.servlet.service;

import com.user.servlet.dao.TicketDao;
import com.user.servlet.dto.TicketDto;
import com.user.servlet.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();

    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId)
                .stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .flightId(ticket.getFlightId())
                        .seatNo(ticket.getSeatNo()).build())
                .collect(Collectors.toList());
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }


}
