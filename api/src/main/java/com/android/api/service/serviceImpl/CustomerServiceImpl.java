package com.android.api.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Account;
import com.android.api.entity.Cart;
import com.android.api.entity.Customer;
import com.android.api.exception.CustomerNotFoundException;
import com.android.api.repository.CustomerRepository;
import com.android.api.service.CartService;
import com.android.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartService cartService;

    @Override
    public Customer findByEmail(String Email) {
        Customer result = customerRepository.findByEmail(Email).orElseThrow(() -> new CustomerNotFoundException(Email));
        return result;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void createCustomerAfterRegister(Customer customer, Account account) {
        customer.setAccount(account);
        customerRepository.save(customer);
        Customer temp = findByEmail(customer.getEmail());
        Cart cart = new Cart();
        cart.setCustomer(temp);
        cartService.save(cart);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomerForCustomer(Customer customer, Customer newCustomer) {
        customer.setAvatar(newCustomer.getAvatar());
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setPhone(newCustomer.getPhone());
        return customerRepository.save(customer);
    }

    @Override
    public Long countCustomer() {
        return customerRepository.countCustomer();
    }
    
}
