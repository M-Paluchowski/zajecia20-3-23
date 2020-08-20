package com.example.demo.excercises.controller;

import com.example.demo.excercises.model.Animal;
import com.example.demo.excercises.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/zwierzak")
    String getAnimal(@RequestParam(name = "imie") String name, Model model) {
        Optional<Animal> animal = animalRepository.findByName(name);

        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "animal";
        } else {
            return "redirect:/";
        }
    }
}
