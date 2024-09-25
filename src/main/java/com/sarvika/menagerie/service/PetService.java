package com.sarvika.menagerie.service;

import com.sarvika.menagerie.dao.PetRepository;
import com.sarvika.menagerie.entity.Pet;
import com.sarvika.menagerie.exception.MenagerieException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    private static final Log LOGGER = LogFactory.getLog(PetService.class);

    @Autowired
    private PetRepository petRepository;

    public Pet addPet(Pet pet){
        Pet petSaved = petRepository.save(pet);
        LOGGER.info("Pet added " + petSaved);
        return petSaved;
    }

    public List<Pet> listAllPets(){
        Iterable<Pet> pets = petRepository.findAll();
        List<Pet> petList = new ArrayList<>();
        for (Pet pet : pets) {
            petList.add(pet);
        }
        return petList;
    }

    public Pet updatePet(int id, Pet updatedPet) throws MenagerieException {
        Optional<Pet> optionalPet = petRepository.findById(id);
        try {
            if (optionalPet.isPresent()) {
                Pet existingPet = optionalPet.get();

                // Update pet details
                existingPet.setName(updatedPet.getName());
                existingPet.setOwner(updatedPet.getOwner());
                existingPet.setSpecies(updatedPet.getSpecies());
                existingPet.setSex(updatedPet.getSex());
                existingPet.setBirth(updatedPet.getBirth());
                existingPet.setDeath(updatedPet.getDeath());

                // Save the updated pet
                return petRepository.save(existingPet);
            } else {
                throw new MenagerieException("Pet with id " + id + " not found");
            }
        } catch (MenagerieException exception){
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    public void deletePet(int id) throws MenagerieException {
        // Check if pet exists
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id); // Delete the pet by id
        } else {
            throw new MenagerieException("Pet with id " + id + " not found");
        }
    }
}
