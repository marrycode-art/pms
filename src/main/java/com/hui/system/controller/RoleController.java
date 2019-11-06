package com.hui.system.controller;

import com.github.pagehelper.PageInfo;
import com.hui.common.ResultEntity;
import com.hui.system.bean.Role;
import com.hui.system.service.RoleService;
import com.hui.system.service.RoleSourcesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleSourcesService roleSourcesService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getRoleList(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){

        String requestURI = request.getRequestURI();

        Map<String, Object> searchCondition = WebUtils.getParametersStartingWith(request,"search_");

        PageInfo<Role> pageInfo = roleService.getRoleList(requestURI,searchCondition,pageNum);

        return ResultEntity.success().put("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){

        int roleid = roleService.saveInfo(role);

        roleSourcesService.saveInfo(roleid,ids);

        return ResultEntity.success();
    }
}
