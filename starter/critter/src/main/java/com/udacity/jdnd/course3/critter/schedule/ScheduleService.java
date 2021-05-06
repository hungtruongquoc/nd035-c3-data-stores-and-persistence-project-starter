package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository repo;
    private final PetService petService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public ScheduleService(
            ScheduleRepository scheduleRepository,
            PetService petService,
            EmployeeService employeeService,
            CustomerService customerService) {
        this.repo = scheduleRepository;
        this.petService = petService;
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    public ScheduleEntity addSchedule(ScheduleEntity schedule) {
        ScheduleEntity savedSchedule = repo.save(schedule);
        if (schedule.getEmployees() != null) {
            Set<EmployeeEntity> employees = schedule.getEmployees();
            employees.forEach(employee ->
                    employee.getSchedules().add(savedSchedule));
        }
        if (schedule.getPets() != null) {
            Set<PetEntity> pets = schedule.getPets();
            pets.forEach(pet ->
                    pet.getSchedules().add(savedSchedule));
        }
        return savedSchedule;
    }

    public List<ScheduleEntity> getAllSchedules() {
        return repo.findAll();
    }

    public List<ScheduleEntity> getScheduleForPet(Long petId) {
        PetEntity pet = petService.getPetById(petId);
        return repo.findAllByPetsContains(pet);
    }

    public List<ScheduleEntity> getScheduleForEmployee(Long employeeId) {
        EmployeeEntity employee = employeeService.getEmployeeById(employeeId);
        return repo.findAllByEmployeesContains(employee);
    }

    public List<ScheduleEntity> getScheduleForCustomer(Long customerId) {
        List<PetEntity> pets = customerService.getCustomerById(customerId).getPets();
        return repo.findAllByPetsIn(pets);
    }
}