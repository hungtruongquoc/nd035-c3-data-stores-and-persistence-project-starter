package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.ScheduleEntity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees")
    private Set<ScheduleEntity> schedules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Set<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<ScheduleEntity> schedules) {
        this.schedules = schedules;
    }
}