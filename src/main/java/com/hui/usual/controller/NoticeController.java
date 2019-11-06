package com.hui.usual.controller;

import com.github.pagehelper.PageInfo;
import com.hui.common.ResultEntity;
import com.hui.common.StringUtils;
import com.hui.usual.bean.Notice;
import com.hui.usual.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping(value = "/jsonList", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity list(HttpServletRequest request,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Map<String, Object> paramrterMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parseparamrterMapToString(paramrterMap);

        String requestURI = request.getRequestURI();

        PageInfo<Notice> page = noticeService.getNoticeList(pageNum,paramrterMap);

        return ResultEntity.success().put("page",page).put("requestURI",requestURI).put("queryStr",queryStr);
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }

    @RequestMapping(value = "/latestNotice",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity latestNotice(){
        List<Notice> notices = noticeService.latestNotice();
        return ResultEntity.success().put("notices", notices);
    }

    @RequestMapping(value = "/showInfo/{nid}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity showInfo(@PathVariable(value = "nid") Integer nid){
        Notice notice = noticeService.showInfo(nid);
        return ResultEntity.success().put("notice",notice);
    }
}
