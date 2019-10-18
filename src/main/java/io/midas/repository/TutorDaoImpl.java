package io.midas.repository;

import io.midas.model.Tutor;
import io.midas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;



public class TutorDaoImpl implements TutorDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    //save return object
    public boolean save(Tutor tutor) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(tutor);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
                logger.error(e.getMessage());

            }
        }
        if (isSuccess) {
            logger.debug("success");
        }
        return isSuccess;
    }

    @Override
    public boolean update(Tutor tutor) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(tutor);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
                logger.error(e.getMessage());
            }
        }
        if (isSuccess) {
            logger.debug("success");
        }
        return isSuccess;
    }

    @Override
    public boolean delete(String tutorName) {
        String hql = "DELETE Tutor where name = :tutorName";
        int deletedCount = 0;
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query<Tutor> query = session.createQuery(hql);
            query.setParameter("tutorName",tutorName);
            deletedCount = query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) {
                transaction.rollback();
                logger.error(e.getMessage());
            }
        }
        if (isSuccess) {
            logger.debug("success");
        }
        return deletedCount>=1?true:false;
    }

    @Override
    public List<Tutor> getTutors()
    {
        String hql = "FROM Tutor";
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Tutor> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Tutor getTutorByName(String name)
    {
        String hql = "FROM Tutor as s where lower(s.name)=:name";
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Tutor> query = session.createQuery(hql);
            query.setParameter("name",name);
            return query.uniqueResult();

        }
    }
}
