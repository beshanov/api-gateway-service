package com.beshanov.meetupapp.apigatewayservice.models.response;

import java.util.List;

public class GetMyEventsIdsResponse {

    private List<Long> eventsIds;

    public List<Long> getEventsIds() {
        return eventsIds;
    }

    public void setEventsIds(List<Long> eventsIds) {
        this.eventsIds = eventsIds;
    }

    public GetMyEventsIdsResponse() {
    }
}
