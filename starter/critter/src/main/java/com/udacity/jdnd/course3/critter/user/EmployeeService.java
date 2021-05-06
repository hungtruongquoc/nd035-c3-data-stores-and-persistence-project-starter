package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.repo = employeeRepository;
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        return repo.save(employee);
    }

    public void updateEmployee(EmployeeEntity employee) {
        repo.save(employee);
    }

    public EmployeeEntity getEmployeeById(Long employeeId) {
        return repo.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("employee Id " + employeeId + " does not exist in the DB"));
    }

    public List<EmployeeEntity> getEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        return repo.findAllByDaysAvailableContains(dayOfWeek).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}