<%@ page import="com.user.servlet.service.TicketService" %>
<%@ page import="com.user.servlet.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Купленные билеты</h1>
    <ul>
    <%
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        TicketService ticketService = TicketService.getInstance();
        List<TicketDto> tickets = ticketService.findAllByFlightId(flightId);
        for (TicketDto ticket : tickets) {
            out.write(String.format("<li>%s</li>", ticket.getSeatNo()));
        }
    %>
    </ul>
</body>
</html>
