package com.sarvika.menagerie.service;

import com.sarvika.menagerie.dao.PetRepository;
import com.sarvika.menagerie.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet addPet(Pet pet){
        System.out.println(pet.getSex());
        Pet petSaved = petRepository.save(pet);
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
