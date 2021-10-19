package ru.studyit.testclass.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class COrder {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    //UUID userId;
    @ManyToOne
    @JoinColumn(name="owner", nullable=false)
    CUser owner;


    /***************************************************************************************************
     * Список товаров в заказе.
     **************************************************************************************************/

    @ManyToMany
    @JoinTable(
            name = "goods_in_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "good_id"))
    List<CGood> goods;


    public UUID getId()
    {
        return id;
    }
    public CUser getOwner()
    {
        return owner;
    }
    public void setOwner(CUser owner)
    {
        this.owner = owner;
    }

    public List<CGood> getGoods()
    {
        return goods;
    }
    public COrder()
    {
        id = null;
        owner = null;
        goods = new ArrayList<>();
    }
    public COrder(UUID id, CUser owner)
    {
        this.id = id;
        setOwner(owner);

        goods = new ArrayList<>();
    }

}
