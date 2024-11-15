package com.backend.stores_back.controller;

import com.backend.stores_back.exception.ResourceNotFoundException;
import com.backend.stores_back.model.Employee;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.EmployeeRepository;
import com.backend.stores_back.repository.StoreRepository;
import com.backend.stores_back.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/{store_id}")
    public List<Employee> getEmployees(@PathVariable int store_id) {

        return employeeService.getAllEmployees(store_id);
    }

    @PostMapping("/add/{store_id}")
    public Employee addEmployee(@RequestBody Employee newEmployee, @PathVariable int store_id) {

        return employeeService.addEmployee(newEmployee, store_id);
    }

    @PostMapping("/edit/{employee_id}")
    public Employee editEmployee(@RequestBody Employee newEmployee, @PathVariable int employee_id) {
        return employeeService.updateEmployee(newEmployee, employee_id);
    }

    @DeleteMapping("/delete/{store_id}/{employee_id}")
    public void deleteEmployee(@PathVariable int employee_id, @PathVariable int store_id) {
        employeeService.deleteEmployee(employee_id, store_id);
    }

    @GetMapping("/employee/{employee_id}")
    public Employee getEmployee(@PathVariable int employee_id){
        return employeeService.getEmployeeById(employee_id);
    }
}
