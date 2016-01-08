package it.tony.springdao.tables;

import it.tony.springdao.model.Customer;

import javax.persistence.Entity;

@Entity(name = "customer")
public class CustomerTable extends BaseTable<Customer> {
    public CustomerTable(Customer entity) {
        super(entity, Customer.class);
    }

    public CustomerTable() {
        super(null, Customer.class);
    }
}
