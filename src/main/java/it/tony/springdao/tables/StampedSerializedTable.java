package it.tony.springdao.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.tony.springdao.model.WithId;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class StampedSerializedTable<T extends WithId> {
    private T entity;
    private String serializedDto;
    private Class<T> wrapped;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;

    public StampedSerializedTable(T entity, Class<T> wrapped) {
        this.entity = entity;
        this.wrapped = wrapped;

        createdAt = new Date();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
