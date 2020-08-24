package com.example.demo.excercises.controller;

import com.example.demo.excercises.model.Animal;
import com.example.demo.excercises.model.Species;
import com.example.demo.excercises.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Set;

@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/")
    String home(@RequestParam(name = "gatunek", required = false) Species species, Model model) {
        Set<Animal> animals;

        if (species != null) {
            animals = animalRepository.findBySpecies(species);
        } else {
            animals = animalRepository.findAll();
        }

        model.addAttribute("animals", animals);
        return "home";
    }

    @GetMapping("/zwierzak")
    String getOne(@RequestParam(name = "imie") String name, Model model) {
        Optional<Animal> animal = animalRepository.findByName(name);

        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "animal";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/dodaj")
    String getAddForm(Model model) {
        Animal animal = new Animal();

        model.addAttribute("animal", animal);
        model.addAttribute("mode", "add");

        return "animalForm";
    }

    @GetMapping("/edytuj")
    String getEditForm(Model model, @RequestParam(name = "imie") String name){
        Optional<Animal> animal = animalRepository.findByName(name);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            model.addAttribute("mode", "edit");
            return "animalForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/dodaj")
    String add(Animal animal) {

        animalRepository.add(animal);

        return "redirect:/zwierzak?imie=" + animal.getName();
    }

    @PostMapping("/edytuj")
    String edit(Animal animal) {
        Optional<Animal> optionalAnimal = animalRepository.findByName(animal.getName());
        if (optionalAnimal.isPresent()) {
            Animal animalFromRepository = optionalAnimal.get();
            animalFromRepository.setSpecies(animal.getSpecies());
            animalFromRepository.setDescription(animal.getDescription());
            animalFromRepository.setImageUrl(animal.getImageUrl());
            return "redirect:/zwierzak?imie=" + animalFromRepository.getName();
        } else {
            return "redirect:/";
        }
    }
}
