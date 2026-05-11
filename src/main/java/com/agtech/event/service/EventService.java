package com.agtech.event.service;

import com.agtech.core.exception.EventNotFoundException;
import com.agtech.event.model.Event;
import com.agtech.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Integer id) {
        return findEventById(id);
    }

    public Event save(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    public void delete(Integer id) {
        Event event = findEventById(id);

        eventRepository.deleteById(event.getId());
    }

    public Event update(Event updateEvent) {
        Event event = findEventById(updateEvent.getId());

        return eventRepository.save(updateEvent);
    }

    private Event findEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
    }
}
