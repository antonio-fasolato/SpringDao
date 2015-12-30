package it.tony.springdao.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.tony.springdao.model.Customer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name = "customer")
@Access(AccessType.PROPERTY)
public class CustomerTable extends WithUuid {
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

    @Transient
    public Customer getCustomer() {
        return customer;
    }

    @Transient
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
