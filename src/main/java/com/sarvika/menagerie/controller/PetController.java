package com.sarvika.menagerie.controller;

import com.sarvika.menagerie.entity.Pet;
import com.sarvika.menagerie.exception.MenagerieException;
import com.sarvika.menagerie.service.PetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;

    @Autowired
    private PetService petService;

    @PostMapping(value="/pets")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet){
        Pet petSaved = petService.addPet(pet);
        return new ResponseEntity<>(petSaved, HttpStatus.CREATED);
    }

    @GetMapping(value="/pets")
    public ResponseEntity<List<Pet>> listAllPets(@RequestParam(required = false) String species){
        return new ResponseEntity<>(petService.listAllPets(species), HttpStatus.OK);
    }

    @PutMapping(value="/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") @NotNull(message = "{pet.id.blank}") Integer id, @Valid @RequestBody Pet pet) throws MenagerieException {
        return new ResponseEntity<>(petService.updatePet(id, pet), HttpStatus.OK);
    }

    @DeleteMapping(value="/pet/{id}")
    public ResponseEntity<String> deletePet(@PathVariable("id") @NotNull(message = "{pet.id.blank}") Integer id) throws MenagerieException {
        petService.deletePet(id);
        String response = environment.getProperty("API.PET_DELETE_SUCCESS") + id;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
