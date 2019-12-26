package com.beshanov.meetupapp.apigatewayservice.services;

import com.beshanov.meetupapp.apigatewayservice.models.mock.EventMock;
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

    public List<EventMock> getEventsByUserId(Long userId) {
        // вызываем profile-service по userId и просим вернуть все соответствующие ему события (ids)(на которые подписан, и которые создал)
        GetMyEventsIdsResponse eventsIdsResponse = restTemplate.getForObject("http://profile-service/events/?userId=" + userId, GetMyEventsIdsResponse.class);
        List<Long> eventsIds = eventsIdsResponse.getEventsIds();
        List<EventMock> events = new ArrayList<>();
        for (Long eventId : eventsIds) {
            EventMock event = restTemplate.getForObject("http://event-service/events/" + eventId, EventMock.class);
            events.add(event);
        }
        return events;
    }

    public EventMock createEvent(CreateEventRequest createRequest) {
        //передать полученный объект запроса в event-service
        createRequest.setCreationDate(Calendar.getInstance());
        EventMock createdEvent = restTemplate.postForObject("http://event-service/events/", createRequest, EventMock.class);
        //получить от него только что созданное событие и вернуть его на
        return createdEvent;
    }
}
