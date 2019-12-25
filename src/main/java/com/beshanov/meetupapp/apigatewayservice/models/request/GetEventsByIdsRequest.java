package com.beshanov.meetupapp.apigatewayservice.models.request;

import java.util.List;

public class GetEventsByIdsRequest {

    private List<Long> eventsId;

    public GetEventsByIdsRequest() {
    }

    public List<Long> getEventsId() {
        return eventsId;
    }

    public void setEventsId(List<Long> eventsId) {
        this.eventsId = eventsId;
    }
}
