package com.sarvika.menagerie.dto;

import com.sarvika.menagerie.entity.Event;
import com.sarvika.menagerie.entity.Pet;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetEvents {
    private Pet pet;
    private List<Event> eventList;
}
