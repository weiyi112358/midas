package io.midas.repository;

import io.midas.model.Student;
import io.midas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateMappingTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public  void mappingTest(){

        String hql = "FROM Student";
        List<Student> students = null;

        try{

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query<Student> query = session.createQuery(hql);
            students = query.list();
            transaction.commit();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());

        }

        Assert.assertNotNull(students);


    }
}
