package com.hui.system.service;

import com.github.pagehelper.PageInfo;
import com.hui.system.bean.Role;

import java.util.Map;

public interface RoleService {

    int saveInfo(Role role);

    PageInfo<Role> getRoleList(String requestURI, Map<String, Object> searchCondition, Integer pageNum);
}
