package com.beshanov.meetupapp.apigatewayservice.controllers;

import com.beshanov.meetupapp.apigatewayservice.models.Event;
import com.beshanov.meetupapp.apigatewayservice.models.request.CreateEventRequest;
import com.beshanov.meetupapp.apigatewayservice.services.EventsRestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventsController {

    @Autowired
    EventsRestService eventsRestService;

    @PostMapping(value = "/events", consumes = "application/json")
    public Event createEvent(@RequestBody CreateEventRequest createRequest) {
        //ессли я попал сюда, значит есть сессия
        // -> значит есть userId
        Long userId = 1L; // получить реальный id с помощью сессии или из login-service
        createRequest.setAuthorId(userId);
        return eventsRestService.createEvent(createRequest);
    }

    @GetMapping("/events")
    @HystrixCommand(defaultFallback = "getFallbackEvents")
    public List<Event> getMyEvents() {
        //ессли я попал сюда, значит есть сессия
        // -> значит есть userId
        Long userId = 1L; // получить реальный id с помощью сессии или из login-service
        // вызываем profile-service по userId и просим вернуть все соответствующие ему события (ids)(на которые подписан, и которые создал)
        return eventsRestService.getEventsByUserId(userId);
    }

    public List<Event> getFallbackEvents() {
        return new ArrayList<>(){
            {
               add(Event.builder().id("234dsfgbd4323").title("Fallback title").build());
            }
        };
    }
}
