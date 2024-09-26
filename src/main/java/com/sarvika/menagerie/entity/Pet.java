package com.sarvika.menagerie.entity;

import com.sarvika.menagerie.enums.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "{pet.name.blank}")
    private String name;
    @Column(length = 20)
    private String owner;
    @Column(length = 20)
    private String species;
    @Enumerated(EnumType.STRING)  // No need for a custom converter here
    @Column(length = 1)
    private Sex sex;
    @PastOrPresent(message = "{pet.birth.invalid}")
    private LocalDate birth;
    @PastOrPresent(message = "{pet.death.invalid}")
    private LocalDate death;
}
