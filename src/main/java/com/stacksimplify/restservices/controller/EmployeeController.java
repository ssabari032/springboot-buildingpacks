package com.stacksimplify.restservices.controller;

import com.stacksimplify.restservices.entity.EmployeeManagement;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value="/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeManagement> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public EmployeeManagement GetEmployeeById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping()
    public EmployeeManagement createEmployee(@Valid @RequestBody  EmployeeManagement employeeManagement){
        return employeeService.createEmployee(employeeManagement);
    }

    @PutMapping("/{id}")
    public EmployeeManagement updateEmployee(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody EmployeeManagement employeeManagement) throws UserNotFoundException {
        return employeeService.updateEmployee(id,employeeManagement);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") @Min(1) Long id){
        employeeService.deleteEmployee(id);
    }

}
