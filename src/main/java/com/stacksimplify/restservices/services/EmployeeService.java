package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entity.EmployeeManagement;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeManagement> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public EmployeeManagement getEmployeeById(Long id) throws UserNotFoundException{
        Optional<EmployeeManagement> employeeOptional = employeeRepository.findById(id);
        if(!employeeOptional.isPresent()){
            throw new UserNotFoundException("Employee is Not Found");
        }
        return employeeOptional.get();
    }

    public EmployeeManagement createEmployee(EmployeeManagement employeeManagement){
        return employeeRepository.save(employeeManagement);
    }

    public String deleteEmployee(Long id){
        employeeRepository.deleteById(id);
        return "Employee Deleted Successfully";
    }

    public EmployeeManagement updateEmployee(Long id, EmployeeManagement employeeManagement) throws UserNotFoundException {
        Optional<EmployeeManagement> employeeOptional= employeeRepository.findById(id);
        if(!employeeOptional.isPresent()){
            throw new UserNotFoundException("Employee is not found. Please enter Valid Employee id");
        }
        employeeManagement.setEmpid(id);
        return employeeRepository.save(employeeManagement);

    }
}
