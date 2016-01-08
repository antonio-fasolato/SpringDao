package it.tony.springdao.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.tony.springdao.model.WithId;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class BaseTable<T extends WithId> {
    private T entity;
    private String serializedDto;
    private Class<T> wrapped;

    public BaseTable(T entity, Class<T> wrapped) {
        this.entity = entity;
        this.wrapped = wrapped;
    }

    @Transient
    public T getEntity() {
        return entity;
    }

    @Transient
    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getSerializedDto() {
        Gson g = new GsonBuilder().create();
        serializedDto = g.toJson(entity);
        return serializedDto;
    }

    public void setSerializedDto(String serializedDto) {
        this.serializedDto = serializedDto;
        Gson g = new GsonBuilder().create();
        entity = g.fromJson(this.serializedDto, wrapped);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return entity == null ? null : entity.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public void setId(Integer id) {
        if (entity != null) {
            entity.setId(id);
        }
    }
}
