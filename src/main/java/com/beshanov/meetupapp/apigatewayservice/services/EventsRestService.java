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
        // вызываем profile-service по userId и просим вернуть все соответствующие ему события (ids)(на которые подписан, и которые создал)
        GetMyEventsIdsResponse eventsIdsResponse = restTemplate.getForObject("http://profile-service/events/?userId=" + userId, GetMyEventsIdsResponse.class);
        List<String> eventsIds = eventsIdsResponse.getEventsIds();
        List<Event> events = new ArrayList<>();
        for (String eventId : eventsIds) {
            Event event = restTemplate.getForObject("http://event-service/events/" + eventId, Event.class);
            events.add(event);
        }
        return events;
    }

    public Event createEvent(CreateEventRequest createRequest) {
        //передать полFученный объект запроса в event-service
        createRequest.setCreationDate(Calendar.getInstance());
        Event createdEvent = restTemplate.postForObject("http://event-service/events/", createRequest, Event.class);
        //получить от него только что созданное событие и вернуть его на
        return createdEvent;
    }
}
