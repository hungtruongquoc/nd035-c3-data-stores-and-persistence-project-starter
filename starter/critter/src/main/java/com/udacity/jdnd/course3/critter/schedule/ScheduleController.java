package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.DataMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final DataMapper converter;

    public ScheduleController(ScheduleService scheduleService, DataMapper converter) {
        this.scheduleService = scheduleService;
        this.converter = converter;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = scheduleService.addSchedule(converter.convertScheduleDTOToEntity(scheduleDTO));
        return converter.convertEntityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleEntity> schedules = scheduleService.getAllSchedules();
        return converter.convertEntitiesToScheduleDTOList(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleEntity> schedules = scheduleService.getScheduleForPet(petId);
        return converter.convertEntitiesToScheduleDTOList(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleEntity> schedules = scheduleService.getScheduleForEmployee(employeeId);
        return converter.convertEntitiesToScheduleDTOList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleEntity> schedules = scheduleService.getScheduleForCustomer(customerId);
        return converter.convertEntitiesToScheduleDTOList(schedules);
    }
}