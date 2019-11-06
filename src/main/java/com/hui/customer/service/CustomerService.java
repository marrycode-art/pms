package com.hui.customer.service;

import com.hui.customer.bean.Customer;

import java.util.List;

public interface CustomerService {

    public void saveInfo(Customer customer);

    List<Customer> getCustomerList();

    Customer searchCusById(Integer id);

    void updateInfo(Customer customer);

    boolean batchDel(Integer[] ids);

    List<Customer> search(Integer cid, String keyword, Integer orderby);

    List<Customer> getComnameList();

    Customer getManagerInfo(Integer id);

    List<Customer> searchAllComName();

    void batchInsert(List<Customer> customers);
}
