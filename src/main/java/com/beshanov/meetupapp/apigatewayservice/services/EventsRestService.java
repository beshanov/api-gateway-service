package com.beshanov.meetupapp.apigatewayservice.services;

import com.beshanov.meetupapp.apigatewayservice.models.Event;
import com.beshanov.meetupapp.apigatewayservice.models.request.CreateEventRequest;
import com.beshanov.meetupapp.apigatewayservice.models.response.GetMyEventsIdsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class EventsRestService {

    @Autowired
    RestTemplate restTemplate;

    public List<Event> getEventsByUserId(Long userId) {
        List<String> eventsIds = getEventIdsByUserId(userId);
        List<Event> events = new ArrayList<>();
        for (String eventId : eventsIds) {
            Event event = restTemplate.getForObject("http://event-service/events/" + eventId, Event.class);
            events.add(event);
        }
        return events;
    }

    public Event createEvent(CreateEventRequest createRequest) {
        createRequest.setCreationDate(Calendar.getInstance());
        Event createdEvent = restTemplate.postForObject("http://event-service/events/", createRequest, Event.class);
        return createdEvent;
    }

    private List<String> getEventIdsByUserId(Long userId) {
        GetMyEventsIdsResponse eventsIdsResponse =
                restTemplate.getForObject("http://profile-service/events/?userId=" + userId, GetMyEventsIdsResponse.class);
        return eventsIdsResponse.getEventsIds();
    }
}
