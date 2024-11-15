package com.backend.stores_back.service;

import com.backend.stores_back.exception.ResourceNotFoundException;
import com.backend.stores_back.model.Employee;
import com.backend.stores_back.model.Store;
import com.backend.stores_back.repository.EmployeeRepository;
import com.backend.stores_back.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final StoreRepository storeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, StoreRepository storeRepository) {
        this.employeeRepository = employeeRepository;
        this.storeRepository = storeRepository;
    }

    public List<Employee> getAllEmployees(int store_id) {
        Optional<Store> store = storeRepository.findById(store_id);

        return store.get().getEmployees();
    }

    public Employee getEmployeeById(int employee_id) {

        return employeeRepository.findById(employee_id).get();
    }

    public Employee addEmployee(Employee newEmployee, int store_id) {
        Employee employee = storeRepository.findById(store_id).map(store -> {
            newEmployee.setStore(store);
            return employeeRepository.save(newEmployee);
        }).orElseThrow(() -> new ResourceNotFoundException("Store with " + store_id + " not found"));

        return employee;
    }

    public Employee updateEmployee(Employee newEmployee, int employee_id) {
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with " + employee_id + " not found"));

        employee.setName(newEmployee.getName());
        employee.setAddress(newEmployee.getAddress());
        employee.setSalary(newEmployee.getSalary());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int employee_id, int store_id) {
        Optional<Employee> employee = employeeRepository.findById(employee_id);

        Optional<Store> store = storeRepository.findById(store_id);

        store.get().getEmployees().remove(employee.get());

        employeeRepository.deleteById(employee_id);
    }

}
