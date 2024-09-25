package com.sarvika.menagerie.dao;

import com.sarvika.menagerie.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {
}
