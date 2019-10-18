package io.midas.repository;

import io.midas.model.Organization;
import io.midas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class OrganizationDaoImpl implements OrganizationDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    //save return object
    public boolean save(Organization organization) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(organization);
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
    public boolean update(Organization organization) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(organization);
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
    public boolean delete(String organizationName) {
        String hql = "DELETE Organization where name = :organizationName";
        int deletedCount = 0;
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query<Organization> query = session.createQuery(hql);
            query.setParameter("organizationName",organizationName);
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
    public List<Organization> getOrganizations()
    {
        String hql = "FROM Organization";
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Organization> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Organization getOrganizationByName(String name)
    {
        String hql = "FROM Organization as s where lower(s.name)=:name";
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Organization> query = session.createQuery(hql);
            query.setParameter("name",name);
            return query.uniqueResult();

        }
    }
}
