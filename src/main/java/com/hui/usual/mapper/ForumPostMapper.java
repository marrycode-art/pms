package com.hui.usual.mapper;

import java.util.List;

import com.hui.usual.bean.ForumPost;
import com.hui.usual.bean.ForumPostExample;
import org.apache.ibatis.annotations.Param;

public interface ForumPostMapper {
    int countByExample(ForumPostExample example);

    int deleteByExample(ForumPostExample example);

    int deleteByPrimaryKey(Integer forumid);

    int insert(ForumPost record);

    int insertSelective(ForumPost record);

    List<ForumPost> selectByExampleWithBLOBs(ForumPostExample example);

    List<ForumPost> selectByExample(ForumPostExample example);

    ForumPost selectByPrimaryKey(Integer forumid);

    int updateByExampleSelective(@Param("record") ForumPost record, @Param("example") ForumPostExample example);

    int updateByExampleWithBLOBs(@Param("record") ForumPost record, @Param("example") ForumPostExample example);

    int updateByExample(@Param("record") ForumPost record, @Param("example") ForumPostExample example);

    int updateByPrimaryKeySelective(ForumPost record);

    int updateByPrimaryKeyWithBLOBs(ForumPost record);

    int updateByPrimaryKey(ForumPost record);
}