package com.hui.usual.controller;

import com.hui.common.ResultEntity;
import com.hui.system.bean.Employee;
import com.hui.system.service.EmployeeService;
import com.hui.usual.bean.Archives;
import com.hui.usual.service.ArchivesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/archives")
public class ArchivesController {

    @Resource
    private ArchivesService archivesService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/jsonList", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity jsonList(){
        List<Archives> archives = archivesService.jsonList();
        List<Employee> employees = employeeService.getAllEmployee();
        return ResultEntity.success().put("archives",archives).put("employees",employees);
    }
}
