package com.sarvika.menagerie.dao;

import com.sarvika.menagerie.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findByPetId(int petId);
    boolean existsByPetIdAndDate(int petId, LocalDate date);
}
