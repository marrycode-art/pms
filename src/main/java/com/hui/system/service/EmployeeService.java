package com.hui.system.service;

import com.hui.system.bean.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getManagerList();

    public Employee login(Employee employee);

    List<Employee> getAllEmployee();

    List<Employee> getPersonWithEmail(Integer eid);
}
