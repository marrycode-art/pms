package com.hui.information.controller;

import com.hui.common.ResultEntity;
import com.hui.information.bean.Email;
import com.hui.information.service.EmailService;
import com.hui.system.bean.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(HttpSession session, Email email, @Param(value = "targetEmail") String targetEmail){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        email.setEmpFk(eid);
        emailService.saveInfo(email);
        return "redirect:/message-give.jsp";
    }

    @RequestMapping(value = "/getMySendEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getMySendEmail(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Email> emails = emailService.getMySendEmail(eid);
        return ResultEntity.success().put("emails",emails);
    }
}
