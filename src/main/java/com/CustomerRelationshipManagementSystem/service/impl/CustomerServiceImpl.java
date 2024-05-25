package com.CustomerRelationshipManagementSystem.service.impl;

import com.CustomerRelationshipManagementSystem.dao.CustomerDao;
import com.CustomerRelationshipManagementSystem.entity.Customer;
import com.CustomerRelationshipManagementSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public String insertCustomer(Customer customer){

        return customerDao.insertCustomer(customer);

    }

    @Override
    public List<Customer> getCustomerList() {
        return customerDao.getCustomersList();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public String updateCustomer(Customer customer) {

        return customerDao.updateCustomer(customer);
    }

    @Override
    public String deleteCustomer(int id) {
        return customerDao.deleteCustomer(id);
    }

    @Override
    public String insertMultipleCustomers(List<Customer> customers) {

        return customerDao.insertMultipleCustomers(customers);
    }

    @Override
    public List<Customer> getCustomersWithFirstName(String firstName) {

        return customerDao.getCustomersWithFirstName(firstName);
    }

    @Override
    public List<Customer> getCustomersWithAgeLessThan(int age){
        return customerDao.getCustomersWithAgeLessThan(age);
    }

    @Override
    public List<Customer> getCustomersWithAgeGreaterThan(int age) {
        return customerDao.getCustomersWithAgeGreaterThan(age);
    }

    @Override
    public List<Customer> getCustomersByAge(int age) {
        return customerDao.getCustomersByAge(age);
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) {
        return customerDao.getCustomersByLastName(lastName);
    }

    @Override
    public List<Customer> getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }

    @Override
    public List<Customer> getCustomerByMobile(String mobile) {
        return customerDao.getCustomerByMobile(mobile);
    }

    @Override
    public String updateCustomerFirstName(int id, String firstName) {
        return customerDao.updateCustomerFirstName(id, firstName);
    }

    @Override
    public String updateCustomerLastName(int id, String lastName) {
        return customerDao.updateCustomerLastName(id, lastName);
    }

    @Override
    public String updateCustomerEmail(int id, String email) {
        return customerDao.updateCustomerEmail(id, email);
    }

    @Override
    public String updateCustomerMobile(int id, String mobile) {
        return customerDao.updateCustomerMobile(id, mobile);
    }

    @Override
    public String updateCustomerAge(int id, int age) {
        return customerDao.updateCustomerAge(id, age);
    }

    @Override
    public List<String> getCustomerNames() {
        return customerDao.getCustomerNames();
    }


}
