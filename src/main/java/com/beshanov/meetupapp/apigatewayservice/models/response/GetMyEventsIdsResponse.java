package com.beshanov.meetupapp.apigatewayservice.models.response;

import java.util.List;

public class GetMyEventsIdsResponse {

    private List<String> eventsIds;

    public List<String> getEventsIds() {
        return eventsIds;
    }

    public void setEventsIds(List<String> eventsIds) {
        this.eventsIds = eventsIds;
    }

    public GetMyEventsIdsResponse() {
    }
}
