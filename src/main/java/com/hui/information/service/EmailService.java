package com.hui.information.service;

import com.hui.information.bean.Email;

import java.util.List;

public interface EmailService {

    void saveInfo(Email email);

    List<Email> getMySendEmail(Integer eid);
}
