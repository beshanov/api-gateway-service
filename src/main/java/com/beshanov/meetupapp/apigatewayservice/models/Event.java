package com.beshanov.meetupapp.apigatewayservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Event {

    private String id;
    private String title;
    private Long authorId;
    private Calendar date;
    private Calendar creationDate;
    private String description;
    private String eventType;
    private String place;
    private String location;
    private String reference;
    private Integer participantsNumber;
    private Integer participantsAgeFrom;
    private Integer participantsAgeTo;
    private String privacyCategory;
}

