package ru.studyit.testclass.model;

import java.util.ArrayList;
import java.util.UUID;

public class COrder {

    UUID id;
    UUID userId;
    //Подразумеваем, что в одном заказе может быть несколько одинаковых товаров,
    //у них будут дублировать идентификаторы в этом списке.
    ArrayList<UUID> goodIds ;


    public UUID getId()
    {
        return id;
    }
    public UUID getUserId()
    {
        return userId;
    }
    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public ArrayList<UUID> getGoods()
    {

        return goodIds;
    }

    public COrder(UUID id, UUID userId)
    {
        this.id = id;
        setUserId(userId);

        goodIds = new ArrayList<>();
    }

}
