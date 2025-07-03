package com.example.eve_anton.Service;

import com.example.eve_anton.Entity.Event;
import com.example.eve_anton.Repository.EventRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  public List<Event> getAllEvent() {
    return eventRepository.findAll();
  }

  public Event getEventById(Long id) {
    return eventRepository.findById(id).orElse(null);
  }

  public void saveEvent(Event event) {
    eventRepository.save(event);
  }

  public Event updateEvent(Event eventUpdate) {
    return eventRepository.save(eventUpdate);
  }

  public void deleteEvent(long id) {
    eventRepository.deleteById(id);
  }
}
