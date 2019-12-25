package com.beshanov.meetupapp.apigatewayservice.controllers;

import com.beshanov.meetupapp.apigatewayservice.models.mock.EventMock;
import com.beshanov.meetupapp.apigatewayservice.models.request.GetEventsByIdsRequest;
import com.beshanov.meetupapp.apigatewayservice.models.response.GetMyEventsIdsResponse;
import com.beshanov.meetupapp.apigatewayservice.models.response.ListEventsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class EventsController {

    @GetMapping("/events")
    public List<EventMock> getMyEvents() {
        //ессли я попал сюда, значит есть сессия
        // -> значит есть userId
        Long userId = 1L; // получить реальный id с помощью сессии или из login-service
        // вызываем profile-service по userId и просим вернуть все соответствующие ему события (ids)(на которые подписан, и которые создал)
        RestTemplate restTemplate = new RestTemplate();
        GetMyEventsIdsResponse eventsIds = restTemplate.getForObject("http://localhost:8081/events/" + userId, GetMyEventsIdsResponse.class);
        //по всем этим событиям идем в event-service за подробной информацией по каждому событию
        List<Long> eventsIdsList = eventsIds.getEventsIds();
        GetEventsByIdsRequest eventsRequest = new GetEventsByIdsRequest();
        eventsRequest.setEventsId(eventsIdsList);
        RestTemplate restTemplate2 = new RestTemplate();
        // ожидаем получить список сущностей событий
        ListEventsResponse listEventsResponse = restTemplate2.postForObject("http://localhost:8082/events/", eventsRequest, ListEventsResponse.class);
        return listEventsResponse.getEvents();
    }
}
