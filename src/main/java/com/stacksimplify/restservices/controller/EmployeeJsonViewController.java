package com.stacksimplify.restservices.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entity.EmployeeManagement;
import com.stacksimplify.restservices.entity.EmployeeViews;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping(value="/jsonviews/employees/")
public class EmployeeJsonViewController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/normalviews/{id}")
    @JsonView(EmployeeViews.normalViews.class)
    public EmployeeManagement GetEmployeeByIdNormalView(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/managerviews/{id}")
    @JsonView(EmployeeViews.managerViews.class)
    public EmployeeManagement GetEmployeeByIdManagerView(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/hrviews/{id}")
    @JsonView(EmployeeViews.hrViews.class)
    public EmployeeManagement GetEmployeeByIdHrView(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return employeeService.getEmployeeById(id);
    }
}
