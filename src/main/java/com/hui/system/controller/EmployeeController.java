package com.hui.system.controller;

import com.hui.common.ResultEntity;
import com.hui.system.bean.Employee;
import com.hui.system.bean.Sources;
import com.hui.system.service.EmployeeService;
import com.hui.system.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String validateCode = (String) session.getAttribute("validateCode");
        if(code.equalsIgnoreCase(validateCode)) {
            session.removeAttribute("validateCode");
            employee = employeeService.login(employee);
            if(employee != null) {

                Integer eid = employee.getEid();
                List<Sources> sources = sourcesService.getSourcesListByEid(eid);
                session.setAttribute("sources", sources);

                session.setAttribute("loginUser",employee);
                return "redirect:/index.jsp";
            }else {
                attributes.addFlashAttribute("message","用户名或密码错误！");
                return "redirect:/login";
            }
        }
        attributes.addFlashAttribute("message","验证码错误！");
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/getManagerList", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagerList(){
        return employeeService.getManagerList();
    }

    @RequestMapping(value = "/getPersonWithEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getPersonWithEmail(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Employee> employees = employeeService.getPersonWithEmail(eid);
        return ResultEntity.success().put("employees",employees);
    }
}
