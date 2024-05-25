package com.CustomerRelationshipManagementSystem.controller;

import com.CustomerRelationshipManagementSystem.dao.CustomerDao;
import com.CustomerRelationshipManagementSystem.entity.Customer;
import com.CustomerRelationshipManagementSystem.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)");

    private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

    @Autowired
    CustomerService customerService;




    @PostMapping("/insertCustomer")
    public ResponseEntity<String> insertCustomer(@RequestBody Customer customer){
        try{

            if((customer == null) || (customer.getEmail() == null) || (!EMAIL_PATTERN.matcher(customer.getEmail()).matches()))
                throw new Exception("Email ID Invalid");

            String resp = customerService.insertCustomer(customer);

            if(!resp.equalsIgnoreCase("Customer Inserted Successfully"))
                throw new Exception(resp);

            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }


    }


    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getCustomersList(){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerList());
        }

        catch(Exception e){
            logger.error("Error while trying to get all customer: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


        }
    }

    //PathVariable can be used to specify any variables in the request url
    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id){

        try{
            if(id == 0)
                throw new Exception("Id is zero");
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
        }
        catch(Exception e){
            logger.error("Error while trying to get customer by id: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getCustomerByEmail/{email}")
    public ResponseEntity<List<Customer>> getCustomerByEmail(@PathVariable String email){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByEmail(email));
        }
        catch(Exception e){
            logger.error("Error while trying to get customer by id: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getCustomerByMobile/{mobile}")
    public ResponseEntity<List<Customer>> getCustomerByMobile(@PathVariable String mobile){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByMobile(mobile));
        }
        catch(Exception e){
            logger.error("Error while trying to get customer by id: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){

        try{

            if(customer == null)
                throw new Exception("Request body is null or invalid");
            if(customer.getId() == 0){
                throw new Exception("Id is not given (or) Id is zero");
            }

            Customer existingCustomer = customerService.getCustomerById(customer.getId());

            if(existingCustomer == null){
                throw new Exception("No customer found with id: "+customer.getId());
            }

            if(customer.getEmail() != null)
                existingCustomer.setEmail(customer.getEmail());

            if(customer.getAge() != 0)
                existingCustomer.setAge(customer.getAge());

            if(customer.getMobileNumber() != null)
                existingCustomer.setMobileNumber(customer.getMobileNumber());

            if(customer.getFirstName() != null)
                existingCustomer.setFirstName(customer.getFirstName());

            if(customer.getLastName() != null)
                existingCustomer.setLastName(customer.getLastName());
            String resp = customerService.updateCustomer(existingCustomer);
            if (!resp.equalsIgnoreCase("Customer Updated Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error while trying to update customer: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        try{
            if(id == 0)
                throw new Exception("Id is empty or 0");

            String resp = customerService.deleteCustomer(id);
            if (!resp.equalsIgnoreCase("Deleted Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);

        }
        catch(Exception e){
            logger.error("Error occurred while trying to delete customer: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/getCustomersWithFirstName/{firstName}")
    public ResponseEntity<List<Customer>> getCustomersWithFirstName(@PathVariable String firstName){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersWithFirstName(firstName));
    }

    @GetMapping("/getCustomersWithLastName/{lastName}")
    public ResponseEntity<List<Customer>> getCustomersWithLastName(@PathVariable String lastName){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersByLastName(lastName));
    }

    @GetMapping("/getCustomersWithAgeLessThanOrEqualTo/{age}")
    public ResponseEntity<List<Customer>> getCustomersWithAgeLessThan(@PathVariable int age){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersWithAgeLessThan(age));
    }

    @GetMapping("/getCustomersWithAgeGreaterThanOrEqualTo/{age}")
    public ResponseEntity<List<Customer>> getCustomersWithAgeGreaterThan(@PathVariable int age){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersWithAgeGreaterThan(age));
    }

    @GetMapping("/getCustomersWithAgeEqualTo/{age}")
    public ResponseEntity<List<Customer>> getCustomersByAge(@PathVariable int age){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomersByAge(age));
    }

    @PostMapping("/insertMultipleCustomers")
    public ResponseEntity<String> insertMultipleCustomers(@RequestBody List<Customer> customers){
        try{
            String resp = customerService.insertMultipleCustomers(customers);
            if (!resp.equalsIgnoreCase("Multiple Customers Added Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error occurred while deleting customer: {}" ,e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/updateCustomerFirstName/{id}")
    public ResponseEntity<String> updateCustomerFirstName(@PathVariable int id, @RequestBody Map<String, String> requestMap){

        try{
            String firstName = requestMap.get("firstName");

            String resp = customerService.updateCustomerFirstName(id, firstName);
            if (!resp.equalsIgnoreCase("Customer's First Name Updated Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error while trying to update customer's first name: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }


    @PutMapping("/updateCustomerLastName/{id}")
    public ResponseEntity<String> updateCustomerLastName(@PathVariable int id, @RequestBody Map<String, String> requestMap){

        try{
            String lastName = requestMap.get("lastName");

            String resp = customerService.updateCustomerLastName(id, lastName);
            if (!resp.equalsIgnoreCase("Customer's Last Name Updated Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error while trying to update customer's Last Name: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    @PutMapping("/updateCustomerMobileNumber/{id}")
    public ResponseEntity<String> updateCustomerMobileNumber(@PathVariable int id, @RequestBody Map<String, String> requestMap){

        try{
            String mobile = requestMap.get("mobileNumber");

            String resp = customerService.updateCustomerMobile(id, mobile);
            if (!resp.equalsIgnoreCase("Customer's Mobile Number Updated Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error while trying to update customer's Mobile Number: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }


    @PutMapping("/updateCustomerAge/{id}")
    public ResponseEntity<String> updateCustomerAge(@PathVariable int id, @RequestBody Map<String, Integer> requestMap){

        try{
            int age = requestMap.get("age");

            String resp = customerService.updateCustomerAge(id, age);
            if (!resp.equalsIgnoreCase("Customer's Age Updated Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error while trying to update customer's age: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }
    @PutMapping("/updateCustomerEmail/{id}")
    public ResponseEntity<String> updateCustomerEmail(@PathVariable int id, @RequestBody Map<String, String> requestMap){

        try{
            String email = requestMap.get("Email");

            String resp = customerService.updateCustomerEmail(id, email);
            if (!resp.equalsIgnoreCase("Customer's Email Updated Successfully"))
                throw new Exception(resp);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }

        catch(Exception e){
            logger.error("Error while trying to update customer's Email: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    @GetMapping("/customerNames")
    public ResponseEntity<List<String>> getCustomerNames(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerNames());
        }
        catch(Exception e){
            logger.error("Error occurred while fetching full names: {}",e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

}
