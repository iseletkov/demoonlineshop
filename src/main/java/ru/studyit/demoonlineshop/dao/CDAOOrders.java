package ru.studyit.demoonlineshop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.studyit.demoonlineshop.model.CGood;
import ru.studyit.demoonlineshop.model.COrder;
import ru.studyit.demoonlineshop.model.CUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDAOOrders implements IDAO<COrder>{
    private SessionFactory sessionFactory;
    public CDAOOrders(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public COrder get(UUID id)
    {
        COrder user = null;
        try(Session session = sessionFactory.openSession())
        {
            user = session.get(COrder.class, id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<COrder> getAll(){
        List<COrder> users;
        try(Session session = sessionFactory.openSession())
        {
            users = session.createQuery("from COrder").list();
        }
        catch(Exception e)
        {
            users = new ArrayList<>();
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public void save(COrder user)
    {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.save(user);


            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void saveList(List<COrder> users)
    {
        try(Session session = sessionFactory.openSession())
        {
            for (int i=0; i<users.size(); i++) {
                session.beginTransaction();
                for (int j = 0; j<1000 && i<users.size(); j++, i++)
                    session.save(users.get(i));
                session.getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void update(COrder user)
    {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(COrder user)
    {
        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public List<COrder> getAllWithGoods(){
        List<COrder> users;
        try(Session session = sessionFactory.openSession())
        {
            users = session.createQuery("SELECT o from COrder o JOIN fetch o.goods").list();
        }
        catch(Exception e)
        {
            users = new ArrayList<>();
            e.printStackTrace();
        }
        return users;

    }
    public List<COrder> getByGood(CGood good){
        List<COrder> orders;
        try(Session session = sessionFactory.openSession())
        {
            Query<COrder> query = session.createQuery("SELECT o from COrder o JOIN o.goods g where g.id=:id");
            query.setParameter("id", good.getId());

            orders =         query.list();
        }
        catch(Exception e)
        {
            orders = new ArrayList<>();
            e.printStackTrace();
        }
        return orders;

    }
}
