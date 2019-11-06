package com.hui.system.controller;

import com.hui.common.ResultEntity;
import com.hui.system.bean.Sources;
import com.hui.system.service.SourcesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/sources")
public class SourcesController {

    @Resource
    private SourcesService sourcesService;

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResultEntity delete(@Param(value = "id") Integer id){
        boolean success = sourcesService.delete(id);
        if (success){
            return ResultEntity.success();
        }else {
            return ResultEntity.failed();
        }
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Sources sources){
        Boolean success = sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/getOneById/{id}",method = RequestMethod.GET)
    public ModelAndView getOneById(@PathVariable(value = "id") Integer id){
        Sources sources = sourcesService.getOneById(id);
        ModelAndView modelAndView = new ModelAndView("pm-edit");
        modelAndView.addObject("onesource",sources);
        return modelAndView;
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Sources sources){
        boolean success = sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }

    @RequestMapping(value = "/getSourcesList", method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){
        List<Sources> list = sourcesService.getSourcesListByPid(0);
        getChildrenList(list.get(0));
        return list;
    }

    private void getChildrenList(Sources sources) {
        Integer id = sources.getId();
        List<Sources> list = sourcesService.getSourcesListByPid(id);

        for (Sources sources1 : list) {
            getChildrenList(sources1);
        }
        sources.setChildren(list);
    }
}
