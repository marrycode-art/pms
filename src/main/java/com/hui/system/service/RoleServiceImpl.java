package com.hui.system.service;

import com.github.pagehelper.PageInfo;
import com.hui.system.bean.Role;
import com.hui.system.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int saveInfo(Role role) {
        roleMapper.saveInfo(role);
        return role.getRoleid();
    }

    @Override
    public PageInfo<Role> getRoleList(String requestURI, Map<String, Object> searchCondition, Integer pageNum) {

        return null;
    }
}
