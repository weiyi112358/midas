package io.midas.repository;

import io.midas.ApplicationBoot;
import io.midas.model.Student;
import io.midas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class HibernateMappingTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public  void mappingTest(){

        String hql = "FROM Student";
        List<Student> students = null;
        int expected=0;

        try{

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query<Student> query = session.createQuery(hql);
            students = query.list();
            transaction.commit();
            expected = 2;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }

        Assert.assertEquals(expected,students.size());


    }
}
