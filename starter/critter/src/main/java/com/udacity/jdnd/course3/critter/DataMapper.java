package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.schedule.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataMapper {
    private final CustomerService customerService;
    private final PetService petService;
    private final EmployeeService employeeService;

    public DataMapper(
            CustomerService customerService,
            PetService petService,
            EmployeeService employeeService) {
        this.customerService = customerService;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    public PetEntity convertPetDTOtoEntity(PetDTO petDTO) {
        PetEntity pet = new PetEntity();
        BeanUtils.copyProperties(petDTO, pet);
        Long ownerId = petDTO.getOwnerId();
        if (ownerId != null) {
            CustomerEntity customer = customerService.getCustomerById(petDTO.getOwnerId());
            pet.setOwner(customer);
        }
        return pet;
    }

    public PetDTO convertEntityToPetDTO(PetEntity pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if (pet.getOwner() != null) {
            Long ownerId = pet.getOwner().getId();
            petDTO.setOwnerId(ownerId);
        }
        return petDTO;
    }

    public List<PetDTO> convertEntitiesToPetDTOList(List<PetEntity> pets) {
        return pets.stream()
                .map(this::convertEntityToPetDTO)
                .collect(Collectors.toList());
    }

    public CustomerEntity convertCustomerDTOToEntity(CustomerDTO customerDTO) {
        CustomerEntity customer = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customer);
        if (customerDTO.getPetIds() != null) {
            List<PetEntity> pets =
                    customerDTO.getPetIds().stream()
                            .map(petService::getPetById)
                            .collect(Collectors.toList());
            customer.setPets(pets);
        }
        return customer;
    }

    public CustomerDTO convertEntityToCustomerDTO(CustomerEntity customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if (customer.getPets() != null) {
            List<Long> petIds =
                    customer.getPets().stream()
                            .map(PetEntity::getId)
                            .collect(Collectors.toList());
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    public List<CustomerDTO> convertEntitiesToCustomerDTOList(List<CustomerEntity> customers) {
        return customers.stream()
                .map(this::convertEntityToCustomerDTO)
                .collect(Collectors.toList());
    }

    public EmployeeEntity convertEmployeeDTOToEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public EmployeeDTO convertEntityToEmployeeDTO(EmployeeEntity employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public List<EmployeeDTO> convertEntitiesToEmployeeList(List<EmployeeEntity> employees) {
        return employees.stream()
                .map(this::convertEntityToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public ScheduleEntity convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = new ScheduleEntity();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if (scheduleDTO.getEmployeeIds() != null) {
            Set<EmployeeEntity> employees =
                    scheduleDTO.getEmployeeIds().stream()
                            .map(employeeService::getEmployeeById)
                            .collect(Collectors.toSet());
            schedule.setEmployees(employees);
        }
        if (scheduleDTO.getPetIds() != null) {
            Set<PetEntity> pets =
                    scheduleDTO.getPetIds().stream()
                            .map(petService::getPetById)
                            .collect(Collectors.toSet());
            schedule.setPets(pets);
        }
        return schedule;
    }

    public ScheduleDTO convertEntityToScheduleDTO(ScheduleEntity schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if (schedule.getEmployees() != null) {
            List<Long> employeesIds =
                    schedule.getEmployees().stream()
                            .map(EmployeeEntity::getId)
                            .collect(Collectors.toList());
            scheduleDTO.setEmployeeIds(employeesIds);
        }
        if (schedule.getPets() != null) {
            List<Long> petIds =
                    schedule.getPets().stream()
                            .map(PetEntity::getId)
                            .collect(Collectors.toList());
            scheduleDTO.setPetIds(petIds);
        }
        return scheduleDTO;
    }

    public List<ScheduleDTO> convertEntitiesToScheduleDTOList(List<ScheduleEntity> schedules) {
        return schedules.stream()
                .map(this::convertEntityToScheduleDTO)
                .collect(Collectors.toList());
    }
}
