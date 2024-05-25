package com.CustomerRelationshipManagementSystem.service;

import com.CustomerRelationshipManagementSystem.entity.Customer;

import java.util.List;

public interface CustomerService {

    String insertCustomer(Customer customer);
    List<Customer> getCustomerList();
    Customer getCustomerById(int id);
    String updateCustomer(Customer customer);
    String deleteCustomer(int id);
    String insertMultipleCustomers(List<Customer> customers);
    List<Customer> getCustomersWithFirstName(String firstName);
    List<Customer> getCustomersWithAgeLessThan(int age);
    List<Customer> getCustomersWithAgeGreaterThan(int age);

    List<Customer> getCustomersByAge(int age);
    List<Customer> getCustomersByLastName(String lastName);

    List<Customer> getCustomerByEmail(String email);

    List<Customer> getCustomerByMobile(String mobile);

    String updateCustomerFirstName(int id, String firstName);

    String updateCustomerLastName(int id, String lastName);

    String updateCustomerEmail(int id, String email);

    String updateCustomerMobile(int id, String mobile);

    String updateCustomerAge(int id, int age);

    List<String> getCustomerNames();
}
