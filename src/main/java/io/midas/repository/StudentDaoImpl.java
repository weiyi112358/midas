package io.midas.repository;

import io.midas.model.Student;
import io.midas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class StudentDaoImpl implements StudentDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    //save return object
    public boolean save(Student student) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(student);
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
    public boolean update(Student student) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(student);
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
    public boolean delete(String studentName) {
        String hql = "DELETE Student where name = :studentName";
        int deletedCount = 0;
        Transaction transaction = null;
        boolean isSuccess = true;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query<Student> query = session.createQuery(hql);
            query.setParameter("studentName",studentName);
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
    public List<Student> getStudents()
    {
        String hql = "FROM Student";
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Student> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Student getStudentByName(String name)
    {
        String hql = "FROM Student as s where lower(s.name)=:name";
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Student> query = session.createQuery(hql);
            query.setParameter("name",name);
            return query.uniqueResult();

        }
    }
}
