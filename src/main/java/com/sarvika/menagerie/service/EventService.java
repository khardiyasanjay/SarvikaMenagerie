package com.sarvika.menagerie.service;

import com.sarvika.menagerie.dao.EventRepository;
import com.sarvika.menagerie.dao.PetRepository;
import com.sarvika.menagerie.dto.PetEvents;
import com.sarvika.menagerie.entity.Event;
import com.sarvika.menagerie.entity.Pet;
import com.sarvika.menagerie.exception.MenagerieException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private static final Log LOGGER = LogFactory.getLog(EventService.class);

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PetRepository petRepository;

    public Event addEventToPet(int petId, LocalDate date, String type, String remark) throws MenagerieException {
        // Checking if the Pet exists
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            throw new MenagerieException("Pet with id " + petId + " not found");
        }

        if (eventRepository.existsByPetIdAndDate(petId, date)) {
            throw new MenagerieException("An event already exists for this pet with given date");
        }

        // Creating new event with existing pet object
        Pet pet = optionalPet.get();
        Event newEvent = new Event();
        newEvent.setPet(pet);
        newEvent.setDate(date);
        newEvent.setType(type);
        newEvent.setRemark(remark);

        return eventRepository.save(newEvent);
    }

    public PetEvents getPetWithEvents(int petId, String sortBy, String sortOrder) throws MenagerieException {
        try {
            Optional<Pet> optionalPet = petRepository.findById(petId);
            if (!optionalPet.isPresent()) {
                throw new MenagerieException("Pet with id " + petId + " not found");
            }

            // Creating the sorting order
            Sort.Direction direction = Sort.Direction.fromString(sortOrder);
            Sort sort = Sort.by(direction, sortBy);


            List<Event> events = eventRepository.findByPetId(petId, sort);

            PetEvents petEvents = new PetEvents();
            petEvents.setPet(optionalPet.get());
            petEvents.setEventList(events);
            return petEvents;
        } catch (MenagerieException exception){
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }
}
