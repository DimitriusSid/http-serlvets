package com.user.servlet.dto;

import lombok.*;

import java.util.Objects;

@Value
@Builder
public class FlightDto {
    Long id;
    String description;
}
