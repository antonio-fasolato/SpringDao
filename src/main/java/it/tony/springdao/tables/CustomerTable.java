package it.tony.springdao.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.tony.springdao.model.Customer;

import javax.persistence.*;

@Entity(name = "customer")
@Access(AccessType.PROPERTY)
public class CustomerTable {
    private String serializedDto;
    @Transient
    private Customer customer;

    public CustomerTable() {
    }

    public CustomerTable(Customer customer) {
        this.customer = customer;
    }

    public String getSerializedDto() {
        Gson g = new GsonBuilder().create();
        serializedDto = g.toJson(customer);
        return serializedDto;
    }

    public void setSerializedDto(String serializedDto) {
        this.serializedDto = serializedDto;
        Gson g = new GsonBuilder().create();
        customer = g.fromJson(this.serializedDto, Customer.class);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return customer == null ? null : customer.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public void setId(Integer id) {
        if (customer != null) {
            customer.setId(id);
        }
    }

    @Transient
    public Customer getCustomer() {
        return customer;
    }

    @Transient
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
