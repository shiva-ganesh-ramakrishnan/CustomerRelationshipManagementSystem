package com.CustomerRelationshipManagementSystem.dao;

import com.CustomerRelationshipManagementSystem.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {

    @Autowired
    SessionFactory sf;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);


    public String insertCustomer(Customer customer) {
        String s = "Customer Inserted Successfully";
        try {
            Session session = sf.openSession();
            //to do crud operations you need to use transaction

            Transaction tr = session.beginTransaction();
            session.persist(customer);
            tr.commit();
            session.close();

            return "Customer Inserted Successfully";
        } catch (Exception e) {
            System.out.println("Error occurred: "+e.getMessage());
            s = "Error occurred";
            logger.error("Error while trying to insert customer: {}", e.getMessage(), e);

        }
        return s;

    }

    public List<Customer> getCustomersList(){

        try{
            Session session = sf.openSession();

            //Here the query table is the entity class on the table name in MySQL
            return session.createQuery("from Customer").list();

        }
        catch(Exception e){
            logger.error("Error occurred: {}" ,e.getMessage(), e);
            return null;
        }

    }

    public Customer getCustomerById(int id){
        try{
            Session session = sf.openSession();
            /*We can either use get or load. The only difference is that
            get -> returns null if object not found in db
            load -> returns ObjectNotFoundException if object is not found in db
            */
            return session.get(Customer.class, id);
        }
        catch(Exception e){
            logger.error("Error occurred: {}" ,e.getMessage(), e);
            return null;
        }
    }


    public String updateCustomer(Customer customer){
        try {
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            session.update(customer);
            tr.commit();
            session.close();
            return "Customer Updated Successfully";
        }
        catch(Exception e){
            logger.error("Error occurred: {}" ,e.getMessage(), e);
            return e.getMessage();
        }
    }


    public String deleteCustomer(int id) {
        try{
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();

            session.delete(session.get(Customer.class, id));
            tr.commit();
            session.close();

            return "Deleted Successfully";
        }
        catch(Exception e){
            logger.error("Error occurred while deleting customer: {}" ,e.getMessage(), e);
            return e.getMessage();
        }

    }

    public String insertMultipleCustomers(List<Customer> customers){
        try{
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();

            for(Customer customer: customers){
                session.save(customer);
            }

            tr.commit();
            session.close();
            return "Multiple Customers Added Successfully";
        }

        catch(Exception e){
            logger.error("Error occurred while inserting multiple customers: {}" ,e.getMessage(), e);
            return e.getMessage();
        }
    }

    public List<Customer> getCustomersWithFirstName(String firstName){
        try{
            Session session = sf.openSession();

            List<Customer> customers = session.createQuery("FROM Customer c WHERE c.firstName LIKE :name", Customer.class)
                    .setParameter("name", "%" + firstName + "%")
                    .getResultList();

            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers with first name: {}" ,e.getMessage(), e);
            return null;
        }
    }

    public List<Customer> getCustomersWithAgeLessThan(int age){
        try{
            Session session = sf.openSession();

            List<Customer> customers = session.createQuery("FROM Customer c WHERE c.age <= :age", Customer.class).setParameter("age", age).list();

            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers with age greater than: {}" ,e.getMessage(), e);
            return null;
        }
    }

    public List<Customer> getCustomersWithAgeGreaterThan(int age){
        try{
            Session session = sf.openSession();

            List<Customer> customers = session.createQuery("FROM Customer c WHERE c.age >= :age", Customer.class).setParameter("age", age).list();

            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers with age greater than: {}" ,e.getMessage(), e);
            return null;
        }
    }


    public List<Customer> getCustomersByAge(int age) {
        try{
            Session session = sf.openSession();
            List<Customer> customers = session.createQuery("from Customer c where c.age = :age", Customer.class).setParameter("age", age).list();
            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers by age: {}" ,e.getMessage(), e);
            return null;
        }
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        try{
            Session session = sf.openSession();
            List<Customer> customers = session.createQuery("from Customer c where c.lastName like :lastName", Customer.class).setParameter("lastName", "%"+lastName+"%").list();
            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers by last name: {}" ,e.getMessage(), e);
            return null;
        }
    }

    public List<Customer> getCustomerByEmail(String email) {
        try{
            Session session = sf.openSession();
            List<Customer> customers = session.createQuery("from Customer c where c.email like :email", Customer.class).setParameter("email", "%"+email+"%").list();
            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers by last name: {}" ,e.getMessage(), e);
            return null;
        }
    }

    public List<Customer> getCustomerByMobile(String mobile) {
        try{
            Session session = sf.openSession();
            List<Customer> customers = session.createQuery("from Customer c where c.mobileNumber like :mobile", Customer.class).setParameter("mobile", "%" + mobile + "%").list();
            return customers;
        }
        catch(Exception e){
            logger.error("Error occurred while getting customers by last name: {}" ,e.getMessage(), e);
            return null;
        }
    }

    public String updateCustomerFirstName(int id, String firstName) {
        try{
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setFirstName(firstName);

            tr.commit();
            session.close();
            return "Customer's First Name Updated Successfully";
        }
        catch(Exception e){
         logger.error("Error occurred when trying to update first name: {}",e.getMessage(), e);
         return e.getMessage();
        }
    }

    public String updateCustomerLastName(int id, String lastName) {
        try{
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setLastName(lastName);
            tr.commit();
            session.close();
            return "Customer's Last Name Updated Successfully";
        }
        catch(Exception e){
            logger.error("Error occurred when trying to update first name: {}",e.getMessage(), e);
            return e.getMessage();
        }
    }

    public String updateCustomerEmail(int id, String email) {
        try {
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setEmail(email);
            tr.commit();
            session.close();
            return "Customer's Email Updated Successfully";
        }
        catch(Exception e){
            logger.error("Error occurred when trying to update Email: {}",e.getMessage(), e);
            return e.getMessage();
        }
    }

    public String updateCustomerMobile(int id, String mobile) {
        try {
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setMobileNumber(mobile);
            tr.commit();
            session.close();
            return "Customer's Mobile Number Updated Successfully";
        }
        catch(Exception e){
            logger.error("Error occurred when trying to update mobile number: {}",e.getMessage(), e);
            return e.getMessage();
        }
    }

    public String updateCustomerAge(int id, int age) {
        try{
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setAge(age);
            tr.commit();
            session.close();

            return "Customer's Age Updated Successfully";
        }
        catch(Exception e){
            logger.error("Error occurred when trying to update mobile number: {}",e.getMessage(), e);
            return e.getMessage();
        }
    }

    public List<String> getCustomerNames(){
        try{
            Session session = sf.openSession();
            List<Name> names = session.createQuery("select c.firstName, c.lastName from Customer c", Name.class).list();
            List<String> fullNames = new ArrayList<>();
            for(Name name: names){
                fullNames.add(name.getFirstName() + " " + name.getLastName() );
            }
            return fullNames;
        }
        catch(Exception e){
            logger.error("Error occurred when trying to fetch full names: {}",e.getMessage(), e);
            return null;
        }
    }
}
