package com.agtech.event.resource;

import com.agtech.event.model.Event;
import com.agtech.event.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    private final static String EVENT_ID = "/{id}";
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        List<Event> list = eventService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(EVENT_ID)
    public ResponseEntity<Event> findById(@PathVariable Integer id) {
        Event event = eventService.findById(id);

        return ResponseEntity.ok().body(event);
    }

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event) {
        Event created = eventService.save(event);

        return ResponseEntity.ok().body(created);
    }

    @DeleteMapping(EVENT_ID)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        eventService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(EVENT_ID)
    public ResponseEntity<Event> update(
            @PathVariable Integer id,
            @RequestBody Event eventUpdate) {
        Event event = eventService.update(id, eventUpdate);

        return ResponseEntity.ok().body(event);
    }
}
