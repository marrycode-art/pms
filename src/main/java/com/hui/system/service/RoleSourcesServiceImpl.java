package com.hui.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hui.system.bean.Role;
import com.hui.system.mapper.RoleSourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class RoleSourcesServiceImpl implements RoleSourcesService {

    @Resource
    private RoleSourcesMapper roleSourcesMapper;

    @Override
    public void saveInfo(int roleid, String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            roleSourcesMapper.saveInfo(roleid,Integer.parseInt(id));
        }
    }
}
















