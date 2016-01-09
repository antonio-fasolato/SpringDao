package it.tony.springdao.services;

import it.tony.springdao.model.Customer;
import it.tony.springdao.repositories.ICustomerRepository;
import it.tony.springdao.tables.CustomerTable;

import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

public class CustomerService extends BaseService<ICustomerRepository, CustomerTable, Customer, Integer> {
    public CustomerService() {
        super(CustomerTable.class);
    }

//    @OrderBy("createdAt desc")
    public Iterable<Customer> getAllOrderByInsertDate() {
//        Iterable<CustomerTable> tList = repository.findAll();
        List<CustomerTable> tList = repository.findAllOrdered();
        List<Customer> cList = new ArrayList<>();
        tList.forEach(t -> cList.add(t.getEntity()));
        return cList;
    }
}
