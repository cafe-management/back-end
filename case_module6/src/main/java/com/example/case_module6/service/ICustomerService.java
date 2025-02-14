package com.example.case_module6.service;
import com.example.case_module6.model.Customer;
import java.util.List;


public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerByEmail(String email);
}
