package com.example.eve_anton.Controller;

import com.example.eve_anton.Entity.Event;
import com.example.eve_anton.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {

  @Autowired
  private EventService eventService;

  @PostMapping("/support/add")
  public String addEvent(@RequestBody Event event){
    eventService.saveEvent(event);
    return "Event Added Successfully..";
  }

  @RequestMapping("/support/{id}")
  public Event getEventById(@PathVariable("id") long id){
    return eventService.getEventById(id);
  }

  @RequestMapping("/support")
  public List<Event> getEvent(){
    return eventService.getAllEvent();
  }

  @PutMapping("/support/{id}")
  public Event updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
    return eventService.updateEvent(event);
  }

  @DeleteMapping("/support/{id}")
  public String deleteEvent(@PathVariable("id") long id){
    eventService.deleteEvent(id);
    return "Event Successfully Deleted";
  }
}
