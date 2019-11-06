package com.hui.information.service;

import com.hui.information.bean.Email;
import com.hui.information.bean.EmailExample;
import com.hui.information.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private EmailMapper emailMapper;

    @Override
    public void saveInfo(Email email) {
        emailMapper.insert(email);
    }

    @Override
    public List<Email> getMySendEmail(Integer eid) {
        EmailExample example = new EmailExample();
        EmailExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        List<Email> emails = emailMapper.selectByExample(example);
        return emails;
    }
}
