package com.hui.customer.service;

import com.hui.customer.bean.Customer;
import com.hui.customer.bean.CustomerExample;
import com.hui.customer.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public void saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        List<Customer> list = customerMapper.selectByExample(new CustomerExample());
        return list;
    }

    @Override
    public Customer searchCusById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateInfo(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public boolean batchDel(Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idsList);
        int row = customerMapper.deleteByExample(example);
        return ids.length == row;
    }

    @Override
    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if (cid == 0) {
            criteria.andComnameLike("%" + keyword + "%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%" + keyword + "%");
        }else if(cid == 1) {
            criteria.andComnameLike("%" + keyword + "%");
        }else {
            criteria.andCompanypersonLike("%" + keyword + "%");
        }
        if (orderby == 1) {
            example.setOrderByClause("id desc");
        }
        List<Customer> list = customerMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Customer> getComnameList() {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public Customer getManagerInfo(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Customer> searchAllComName() {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public void batchInsert(List<Customer> customers) {
        customerMapper.batchInsert(customers);
    }
}
