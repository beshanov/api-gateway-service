package com.beshanov.meetupapp.apigatewayservice.controllers;

import com.beshanov.meetupapp.apigatewayservice.models.mock.EventMock;
import com.beshanov.meetupapp.apigatewayservice.models.request.CreateEventRequest;
import com.beshanov.meetupapp.apigatewayservice.services.EventsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventsController {

    @Autowired
    EventsRestService eventsRestService;

    @PostMapping(value = "/events", consumes = "application/json")
    public EventMock createEvent(@RequestBody CreateEventRequest createRequest) {
        //ессли я попал сюда, значит есть сессия
        // -> значит есть userId
        Long userId = 1L; // получить реальный id с помощью сессии или из login-service
        createRequest.setAuthorId(userId);
        return eventsRestService.createEvent(createRequest);
    }

    @GetMapping("/events")
    public List<EventMock> getMyEvents() {
        //ессли я попал сюда, значит есть сессия
        // -> значит есть userId
        Long userId = 1L; // получить реальный id с помощью сессии или из login-service
        // вызываем profile-service по userId и просим вернуть все соответствующие ему события (ids)(на которые подписан, и которые создал)
        return eventsRestService.getEventsByUserId(userId);
    }
}
