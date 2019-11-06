package com.hui.system.service;

import com.hui.system.bean.Employee;
import com.hui.system.bean.EmployeeExample;
import com.hui.system.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getManagerList() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        return employeeMapper.selectByExample(example);
    }

    @Override
    public Employee login(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());

        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees != null && employees.size() != 0){
            employee = employees.get(0);
            return employee;
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeMapper.selectByExample(new EmployeeExample());
        return employees;
    }

    @Override
    public List<Employee> getPersonWithEmail(Integer eid) {
        return employeeMapper.getPersonWithEmail(eid);
    }
}
