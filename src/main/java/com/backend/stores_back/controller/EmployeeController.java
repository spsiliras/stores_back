package com.backend.stores_back.controller;

import com.backend.stores_back.exception.ResourceNotFoundException;
import com.backend.stores_back.model.Employee;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.EmployeeRepository;
import com.backend.stores_back.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/{store_id}")
    public List<Employee> getEmployees(@PathVariable int store_id) {
        Optional<Store> store = storeRepository.findById(store_id);

        return store.get().getEmployees();
    }

    @PostMapping("/add/{store_id}")
    public Employee addEmployee(@RequestBody Employee newEmployee, @PathVariable int store_id) {
        Employee employee = storeRepository.findById(store_id).map(store -> {
            newEmployee.setStore(store);
            return employeeRepository.save(newEmployee);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return employee;
    }

    @PostMapping("/edit/{employee_id}")
    public Employee editEmployee(@RequestBody Employee newEmployee, @PathVariable int employee_id) {
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with " + employee_id + " not found"));

        employee.setName(newEmployee.getName());
        employee.setAddress(newEmployee.getAddress());
        employee.setSalary(newEmployee.getSalary());

        return employeeRepository.save(employee);
    }

    @DeleteMapping("/delete/{store_id}/{employee_id}")
    public void deleteEmployee(@PathVariable int employee_id, @PathVariable int store_id) {
        Optional<Employee> employee = employeeRepository.findById(employee_id);

        Optional<Store> store = storeRepository.findById(store_id);

        store.get().getEmployees().remove(employee.get());

        employeeRepository.deleteById(employee_id);
    }
}
