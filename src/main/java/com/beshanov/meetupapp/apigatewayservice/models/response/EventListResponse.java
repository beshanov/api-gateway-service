package com.beshanov.meetupapp.apigatewayservice.models.response;

import com.beshanov.meetupapp.apigatewayservice.models.Event;

import java.util.List;

public class EventListResponse {
    private List<Event> events;

    public EventListResponse() {
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
