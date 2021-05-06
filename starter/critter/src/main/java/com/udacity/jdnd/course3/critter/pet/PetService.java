package com.udacity.jdnd.course3.critter.pet;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    private final PetRepository repo;

    public PetService(PetRepository petRepository) {
        this.repo = petRepository;
    }

    public PetEntity addPet(PetEntity pet) {
        PetEntity savedPet = repo.save(pet);
        if (pet.getOwner() != null) {
            pet.getOwner().getPets().add(savedPet);
        }
        return savedPet;
    }

    public PetEntity getPetById(long petId) {
        return repo.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("pet Id " + petId + " does not exist in the DB"));
    }

    public List<PetEntity> getAllPets() {
        return repo.findAll();
    }

    public List<PetEntity> getPetsByOwner(long ownerId) {
        return repo.findAllByOwnerId(ownerId);
    }
}
