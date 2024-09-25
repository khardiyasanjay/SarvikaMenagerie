package com.sarvika.menagerie.service;

import com.sarvika.menagerie.dao.PetRepository;
import com.sarvika.menagerie.entity.Pet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
