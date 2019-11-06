package com.hui.system.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleSourcesMapper {

    void saveInfo(@Param(value = "roleid") int roleid, @Param(value = "id") int id);
}
