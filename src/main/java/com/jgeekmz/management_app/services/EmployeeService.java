package com.jgeekmz.management_app.services;

import com.jgeekmz.management_app.models.Employee;
import com.jgeekmz.management_app.models.User;
import com.jgeekmz.management_app.repositories.EmployeeRepository;
import com.jgeekmz.management_app.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    UserService userService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepo;

    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Get All Employees
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Get Employee By Id
     */
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    /**
     * Delete Employee
     */
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    /**
     * Update Employee
     */
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Get Employee by username
     */
    public Employee findByUsername(String un) {
        return employeeRepository.findByUsername(un);
    }

    /** Find employee by id */
    public List<User> assignUsername(int id) {
        // try {
        Employee emp = employeeRepository.findById(id).orElse(null);
        //List<User> user = userRepo.findByFirstnameAndLastname(emp.getFirstname(), emp.getLastname());

        // List<User> userList = (List<User>) userService.findByNames(emp.getFirstname(), emp.getLastname());
        //log.info(String.valueOf(user));

           /*if (user == null) {
               // TODO
               emp.setUsername("No user found!");
               employeeRepository.save(emp);
           } else if (user != null && user.size() == 1) {
               emp.setUsername("works");
               employeeRepository.save(emp);
           } else if (user != null && user.size() > 1){

           }*/
        // } catch (Exception e) {
        //    throw new UserNotFoundException("We have more than one user with those names!");
        //}

        return userRepo.findByFirstnameAndLastname(emp.getFirstname(), emp.getLastname());
    }

    public List<Employee> findEmployeeByKeaword(String keywordSearch) {
        return employeeRepository.findEmployeeByKeyword(keywordSearch);
    }
}