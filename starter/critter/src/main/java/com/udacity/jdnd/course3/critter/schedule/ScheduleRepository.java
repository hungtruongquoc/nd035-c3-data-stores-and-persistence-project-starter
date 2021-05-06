package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAll();

    List<ScheduleEntity> findAllByPetsContains(PetEntity pet);

    List<ScheduleEntity> findAllByEmployeesContains(EmployeeEntity employee);

    List<ScheduleEntity> findAllByPetsIn(List<PetEntity> pets);
}