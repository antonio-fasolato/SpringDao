package it.tony.springdao.services;

import it.tony.springdao.model.Customer;
import it.tony.springdao.repositories.ICustomerRepository;
import it.tony.springdao.tables.CustomerTable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    public void save(Customer c) {
        if (c.getId() != null && customerRepository.exists(c.getId())) {
            CustomerTable t = customerRepository.findOne(c.getId());
            t.setEntity(c);
            t.setModifiedAt(new Date());
            customerRepository.save(t);
        } else {
            customerRepository.save(new CustomerTable(c));
        }
    }

    public boolean exist(Integer id) {
        return customerRepository.exists(id);
    }

    public void delete(Integer id) {
        customerRepository.delete(id);
    }

    public void delete(Customer c) {
        customerRepository.delete(new CustomerTable(c));
    }

    public Customer get(Integer id) {
        CustomerTable t = customerRepository.findOne(id);
        return t == null ? null : t.getEntity();
    }

    @OrderBy("createdAt desc")
    public Iterable<Customer> getAll() {
        Iterable<CustomerTable> tList = customerRepository.findAll();
        List<Customer> cList = new ArrayList<>();
        tList.forEach(t -> cList.add(t.getEntity()));
        return cList;
    }
}
