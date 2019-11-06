package com.hui.project.controller;

import com.hui.customer.bean.Customer;
import com.hui.customer.service.CustomerService;
import com.hui.project.bean.Project;
import com.hui.project.service.ProjectService;
import com.hui.system.bean.Employee;
import com.hui.system.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        ModelAndView modelAndView = new ModelAndView("project-base");
        List<Project> list = projectService.getProjectList();
        List<Customer> companies = customerService.searchAllComName();
        List<Employee> managers = employeeService.getManagerList();
        modelAndView.addObject("list",list);
        modelAndView.addObject("companies",companies);
        modelAndView.addObject("managers",managers);
        return  modelAndView;
    }

    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(Project project){
        projectService.saveProject(project);
        return "redirect:/project/list";
    }

    @RequestMapping(value = "/searchInfo/{pid}", method = RequestMethod.GET)
    public ModelAndView searchInfo(@PathVariable("pid") Integer pid){
        ModelAndView modelAndView = new ModelAndView("project-base-look");
        Project project = projectService.searchInfo(pid);
        List<Customer> companies = customerService.searchAllComName();
        List<Employee> managers = employeeService.getManagerList();
        modelAndView.addObject("project", project);
        modelAndView.addObject("companies",companies);
        modelAndView.addObject("managers",managers);
        return  modelAndView;
    }

    @RequestMapping(value = "/edit/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView edit(@PathVariable(value = "pid") Integer pid){
        ModelAndView modelAndView = new ModelAndView("project-base-edit");
        Project project = projectService.searchInfo(pid);
        List<Customer> companies = customerService.searchAllComName();
        List<Employee> managers = employeeService.getManagerList();
        modelAndView.addObject("project", project);
        modelAndView.addObject("companies",companies);
        modelAndView.addObject("managers",managers);
        return modelAndView;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Project project){
        projectService.update(project);
        return "redirect:/project/list";
    }

    @RequestMapping(value = "/batchDel", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDel(@Param(value = "ids") Integer ids){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean isSuccess = projectService.batchDel(ids);
        if (isSuccess){
            map.put("statusCode",200);
            map.put("message","删除成功！");
        }else {
            map.put("statusCode",500);
            map.put("message","删除失败！");
        }
        return map;
    }

    @RequestMapping(value = "/conditionSearch", method = RequestMethod.GET)
    public ModelAndView conditionSearch(@RequestParam(value = "cid") Integer cid, @RequestParam(value = "keyword") String keyword, @RequestParam(value = "orderby") Integer orderby){
        ModelAndView modelAndView = new ModelAndView("project-base");
        List<Project> list = projectService.conditionSearch(cid,keyword,orderby);
        List<Customer> companies = customerService.searchAllComName();
        List<Employee> managers = employeeService.getManagerList();
        modelAndView.addObject("companies",companies);
        modelAndView.addObject("managers",managers);
        modelAndView.addObject("list",list);
        return modelAndView;
    }
}
