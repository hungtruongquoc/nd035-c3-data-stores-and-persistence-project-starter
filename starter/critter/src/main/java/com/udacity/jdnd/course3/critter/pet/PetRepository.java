package com.udacity.jdnd.course3.critter.pet;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<PetEntity, Long> {
    List<PetEntity> findAll();

    List<PetEntity> findAllByOwnerId(long ownerId);
}