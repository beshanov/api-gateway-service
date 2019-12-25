package com.beshanov.meetupapp.apigatewayservice.models.response;

import com.beshanov.meetupapp.apigatewayservice.models.mock.EventMock;

import java.util.List;

public class ListEventsResponse {
    private List<EventMock> events;

    public ListEventsResponse() {
    }

    public List<EventMock> getEvents() {
        return events;
    }

    public void setEvents(List<EventMock> events) {
        this.events = events;
    }
}
