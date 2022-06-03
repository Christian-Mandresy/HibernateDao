package com.dao;
import com.model.BaseModel;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository("DaoObject")
public class DaoObject{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(BaseModel daoObject) {
        Session session=this.sessionFactory.openSession();
        Transaction tx=null;

        try {
            tx=session.beginTransaction();
            session.save(daoObject);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }

    }

    public List findall(BaseModel baseModel)
    {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();

            Criteria criteria=session.createCriteria(baseModel.getClass());
            List util = criteria.list();
            tx.commit();
            return util;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }
    }

    public String toUper(String s) {
        char[] c = s.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        Character ct = c[0];
        s = ct.toString();
        for (int i = 1; i < c.length; i++) {
            Character temp = c[i];
            s += temp.toString();
        }
        return s;
    }

    public List find(BaseModel baseModel) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List list = null;
        Field[] field = baseModel.getClass().getDeclaredFields();
        Object obj = null;
        try {
            Criteria criteria = session.createCriteria(baseModel.getClass());
            for (Field field1 : field) {
                obj = baseModel.getClass().getMethod("get" + toUper(field1.getName())).invoke(baseModel);
                if (obj != null) {
                    Criterion newCrit = Restrictions.eq(field1.getName(), obj);
                    criteria.add(newCrit);
                    list = criteria.list();
                } else {
                }
            }
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return list;
    }


}
