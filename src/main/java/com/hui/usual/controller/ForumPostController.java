package com.hui.usual.controller;

import com.hui.common.ResultEntity;
import com.hui.system.bean.Employee;
import com.hui.usual.bean.ForumPost;
import com.hui.usual.service.ForumPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "/post")
public class ForumPostController {

    @Resource
    private ForumPostService forumPostService;

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(HttpSession session, ForumPost forumPost){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        forumPost.setEmpFk3(eid);
        forumPost.setCreatetime(new Date());
        System.err.println(forumPost);

        boolean isSuccess = forumPostService.saveInfo(forumPost);
        if (isSuccess){
            return ResultEntity.success();
        }else {
            return ResultEntity.failed();
        }
    }
}
