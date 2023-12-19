package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.*;

import edu.miu.cs.cs544.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public List<CustomerDto> getAllCustomers() {

        return customerRepository.findAll().stream()
                .map(customer -> CustomerDtoAdapter.toCustomerDto(Optional.ofNullable(customer)))
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public CustomerDto register(CustomerDto request) {
        Customer customer = CustomerDtoAdapter.toCustomer(request);
        customerRepository.save(customer);
        return CustomerDtoAdapter.toCustomerDto(Optional.of(customer));
    }
    @Override
    @Transactional
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto){
       Optional<Customer> oldCustomer = customerRepository.findById(id);
       if(oldCustomer.isEmpty()) return null;
       else{
           Customer customer = oldCustomer.get();
           customer.setLastName(customerDto.getLastName());
           customer.setFirstName(customerDto.getFirstName());
           customer.setEmail(customerDto.getEmail());

           customer.getPhysicalAddress().setPostalCode(customerDto.getPhysicalAddress().getPostalCode());
           customer.getPhysicalAddress().setLine1(customerDto.getPhysicalAddress().getLine1());
           customer.getBillingAddress().setCity(customerDto.getPhysicalAddress().getCity());

           customer.getBillingAddress().setPostalCode(customerDto.getBillingAddress().getPostalCode());
           customer.getBillingAddress().setLine1(customerDto.getBillingAddress().getLine1());
           customer.getBillingAddress().setCity(customerDto.getBillingAddress().getCity());

           Customer newCustomer  = customerRepository.save(customer);
           return CustomerDtoAdapter.toCustomerDto(Optional.of(newCustomer));
       }
    }
@Override
@Transactional
    public CustomerDto getCustomerById(String id){
        Optional<Customer> customer = customerRepository.findById(Integer.valueOf(id));
    return CustomerDtoAdapter.toCustomerDto(customer);
    }
    @Override
    @Transactional
    public CustomerDto getCustomerByLastName(String lastName){
        Optional<Customer> customer = customerRepository.findByLastName(lastName);
        return CustomerDtoAdapter.toCustomerDto(customer);
    }



    @Override
    @Transactional
    public CustomerDto removeCustomerById(Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) return null;
        else {
            customerRepository.deleteCustomerById(id);
            return CustomerDtoAdapter.toCustomerDto(customer);
        }

    }
}
