package ru.studyit.testclass.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.studyit.testclass.model.CGood;
import ru.studyit.testclass.model.CGood;
import ru.studyit.testclass.model.CUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDAOGoods implements IDAO<CGood>{
    private SessionFactory sessionFactory;
    public CDAOGoods(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CGood get(UUID id)
    {
        CGood user = null;
        try(Session session = sessionFactory.openSession())
        {
            user = session.get(CGood.class, id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<CGood> getAll(){
        List<CGood> users;
        try(Session session = sessionFactory.openSession())
        {
            users = session.createQuery("from CGood").list();
        }
        catch(Exception e)
        {
            users = new ArrayList<>();
            e.printStackTrace();
        }
        return users;
    }

    public List<CGood> getAllByUser(CUser user){
        List<CGood> goods;
        try(Session session = sessionFactory.openSession())
        {
            Query<CGood> q = session.createQuery("Select g from CGood g JOIN g.orders o WHERE o.owner=:user");
            q.setParameter("user", user);

            goods = q.list();
        }
        catch(Exception e)
        {
            goods = new ArrayList<>();
            e.printStackTrace();
        }
        return goods;
    }
    @Override
    public void save(CGood user)
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
    public void saveList(List<CGood> users)
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
    public void update(CGood user)
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
    public void delete(CGood user)
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

}
