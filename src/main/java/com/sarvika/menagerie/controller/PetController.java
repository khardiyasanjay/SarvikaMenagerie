package com.sarvika.menagerie.controller;

import com.sarvika.menagerie.entity.Pet;
import com.sarvika.menagerie.service.PetService;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private static final Log LOGGER = LogFactory.getLog(PetController.class);

    @Autowired
    private PetService petService;

    @PostMapping(value="/pets")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet){
        Pet petSaved = petService.addPet(pet);
        return new ResponseEntity<>(petSaved, HttpStatus.CREATED);
    }

    @GetMapping(value="/pets")
    public ResponseEntity<List<Pet>> listAllPets(){
        return new ResponseEntity<>(petService.listAllPets(), HttpStatus.OK);
    }
}
