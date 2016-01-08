package it.tony.springdao.services;

import it.tony.springdao.model.Customer;
import it.tony.springdao.repositories.ICustomerRepository;
import it.tony.springdao.tables.CustomerTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    public void save(Customer c) {
        if(c.getId() != null && customerRepository.exists(c.getId())) {
            CustomerTable t = customerRepository.findOne(c.getId());
            t.setEntity(c);
            t.setModifiedAt(new Date());
            customerRepository.save(t);
        } else {
            customerRepository.save(new CustomerTable(c));
        }
    }
}
