package com.hui.usual.controller;

import com.github.pagehelper.PageInfo;
import com.hui.common.ResultEntity;
import com.hui.common.StringUtils;
import com.hui.system.bean.Employee;
import com.hui.usual.bean.BaoXiao;
import com.hui.usual.service.BaoXiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/bx")
public class BaoXiaoController {

    @Autowired
    private BaoXiaoService baoXiaoService;

    @RequestMapping(value = "/myList", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){

        Map<String, Object> paramrterMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parseparamrterMapToString(paramrterMap);

        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();

        String requestURI = request.getRequestURI();
        //      /pms/bx/myList

        PageInfo<BaoXiao> page = baoXiaoService.getMyBaoXiaoList(eid,pageNum,paramrterMap);
        ModelAndView modelAndView = new ModelAndView("mybaoxiao-base");
        modelAndView.addObject("page",page);
        modelAndView.addObject("queryStr", queryStr);
        modelAndView.addObject("requestURI", requestURI);
        return  modelAndView;
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(HttpSession session, BaoXiao baoXiao){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        baoXiao.setEmpFk(loginUser.getEid());
        baoXiaoService.saveInfo(baoXiao);
        return "redirect:/bx/myList";
    }
}
