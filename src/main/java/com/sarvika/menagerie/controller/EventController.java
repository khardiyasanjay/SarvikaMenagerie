package com.sarvika.menagerie.controller;

import com.sarvika.menagerie.entity.Event;
import com.sarvika.menagerie.exception.MenagerieException;
import com.sarvika.menagerie.service.EventService;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value="/menagerie")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("pets/{id}/{date}")
    public ResponseEntity<Event> addEvent(@PathVariable("id")
                                            @NotBlank(message = "{pet.id.null")  int petId,
                                          @PathVariable("date")
                                            @NotBlank(message = "{event.date.blank}")
                                            @FutureOrPresent(message = "{event.date.invalid}") LocalDate date,
                                          @RequestParam(value = "type", required = false) String type,
                                          @RequestParam(value = "remark", required = false) String remark) throws MenagerieException {
        Event newEvent = eventService.addEventToPet(petId, date, type, remark);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }
}
