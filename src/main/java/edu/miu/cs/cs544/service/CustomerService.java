package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.*;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.StateChangeRequest;

import java.util.List;

public interface CustomerService {



    List<CustomerDto> getAllCustomers();
    CustomerDto register(CustomerDto customerDto);

    CustomerDto updateCustomer(Integer id, CustomerDto customerDto);

    CustomerDto getCustomerById(String id);
    CustomerDto getCustomerByLastName(String lastName);
    CustomerDto removeCustomerById(Integer id);

}
