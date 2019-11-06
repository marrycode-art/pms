package com.hui.usual.service;

import com.hui.usual.bean.ForumPost;
import com.hui.usual.mapper.ForumPostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Resource
    private ForumPostMapper forumPostMapper;

    @Override
    public boolean saveInfo(ForumPost forumPost) {
        int row = forumPostMapper.insert(forumPost);
        if (row > 0) {
            return true;
        }else {
            return false;
        }
    }
}
