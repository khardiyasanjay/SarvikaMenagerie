package com.sarvika.menagerie.controller;

import com.sarvika.menagerie.entity.Pet;
import com.sarvika.menagerie.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/menagerie")
@Validated
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping(value="/pets")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet){
        System.out.println("Pet received: " + pet.getName() + ", Sex: " + pet.getSex());
        Pet petSaved = petService.addPet(pet);
        System.out.println(petSaved);
        return new ResponseEntity<>(petSaved, HttpStatus.CREATED);
    }

    @GetMapping(value="/pets")
    public ResponseEntity<List<Pet>> listAllPets(){
        return new ResponseEntity<>(petService.listAllPets(), HttpStatus.OK);
    }
}
