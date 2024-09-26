package com.sarvika.menagerie.service;

import com.sarvika.menagerie.dao.EventRepository;
import com.sarvika.menagerie.dao.PetRepository;
import com.sarvika.menagerie.entity.Event;
import com.sarvika.menagerie.entity.Pet;
import com.sarvika.menagerie.exception.MenagerieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PetRepository petRepository;

    public Event addEventToPet(int petId, LocalDate date, String type, String remark) throws MenagerieException {
        // Check if the Pet exists
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            throw new MenagerieException("Pet with id " + petId + " not found");
        }

        // Check if an event already exists for the same pet on the same date
        if (eventRepository.existsByPetIdAndDate(petId, date)) {
            throw new MenagerieException("An event already exists for this pet on this date");
        }

        // Create a new event
        Pet pet = optionalPet.get();
        Event newEvent = new Event();
        newEvent.setPet(pet);
        newEvent.setDate(date);
        newEvent.setType(type);
        newEvent.setRemark(remark);

        // Save the event to the repository
        return eventRepository.save(newEvent);
    }
}
