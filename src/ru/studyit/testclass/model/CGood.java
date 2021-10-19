package ru.studyit.testclass.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "goods")
public class CGood {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    public UUID getId()
    {
        return id;
    }

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    private List<COrder> orders;


    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public CGood()
    {
        id = null;
        setName("");
        orders = new ArrayList<>();
    }
    public CGood(UUID id, String name)
    {
        this.id = id;
        setName(name);
        orders = new ArrayList<>();
    }

    public List<COrder> getOrders() {
        return orders;
    }
}
