package com.example.demo.excercises.repository;

import com.example.demo.excercises.model.Animal;
import com.example.demo.excercises.model.Species;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AnimalRepository {

    private Set<Animal> animals;

    public AnimalRepository() {
        animals = new HashSet<>();
        animals.add(new Animal("Azor", "Azort to jest pies", "https://www.zooplus.pl/magazyn/wp-content/uploads/2018/07/szcz%C4%99%C5%9Bliwy-pies.jpg", Species.DOG));
        animals.add(new Animal("Rudy", "Ruby to jest kot", "https://static.fajnyzwierzak.pl/media/uploads/media_image/auto/entry-content/24/mobile/szary-kot-brytyjski.jpg", Species.CAT));
        animals.add(new Animal("Marmozetka", "Marmozetka to jest małpa", "https://skypenguin.ru/assets/kumcopy-lila.jpg", Species.OTHER));
    }

    public Optional<Animal> findByName(String name) {
        return animals.stream()
                .filter(animal -> animal.getName().equals(name))
                .findFirst();
    }

    public Set<Animal> findAll() {
        return new HashSet<>(animals);
    }

    public Set<Animal> findBySpecies(Species species) {
        return animals.stream()
                .filter(animal -> animal.getSpecies() == species)
                .collect(Collectors.toSet());
    }

    public void add(Animal animal) {
        animals.add(animal);
    }
}
