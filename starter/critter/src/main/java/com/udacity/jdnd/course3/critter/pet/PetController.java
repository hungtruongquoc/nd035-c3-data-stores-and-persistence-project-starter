package com.udacity.jdnd.course3.critter.pet;

import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.DataMapper;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService service;
    private final DataMapper converter;

    public PetController(PetService petService, DataMapper converter) {
        this.service = petService;
        this.converter = converter;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEntity pet = service.addPet(converter.convertPetDTOtoEntity(petDTO));
        return converter.convertEntityToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetEntity pet = service.getPetById(petId);
        return converter.convertEntityToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetEntity> pets = service.getAllPets();
        return converter.convertEntitiesToPetDTOList(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetEntity> pets = service.getPetsByOwner(ownerId);
        return converter.convertEntitiesToPetDTOList(pets);
    }
}
