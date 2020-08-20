package com.example.demo.excercises.repository;

import com.example.demo.excercises.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class AnimalRepository {

    private Set<Animal> animals;

    public AnimalRepository() {
        animals = new HashSet<>();
        animals.add(new Animal("Azor", "Azort to jest pies"));
        animals.add(new Animal("Rudy", "Ruby to jest kot"));
    }

    public Optional<Animal> findByName(String name) {
        return animals.stream()
                .filter(animal -> animal.getName().equals(name))
                .findFirst();
    }
}
