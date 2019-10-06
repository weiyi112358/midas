package io.midas.jdbc;

import io.midas.model.Tutor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TutorDaoTest {

    private TutorDao tutorDao;

    @Test
    public void getTutorsTest()
    {
        tutorDao = new TutorDao();
        List<Tutor> tutors = tutorDao.getTutors();
        int expectedNum = 1;
        Assert.assertEquals(expectedNum,tutors.size());

    }

    @Before
    public void setUp()
    {
        tutorDao = new TutorDao();
        Tutor s = new Tutor();
        s.setName("testStudent");

        tutorDao.save(s);
    }

    @After
    public  void tearDown()
    {
        tutorDao = new TutorDao();
        Tutor s = new Tutor();
        s.setName("testStudent");

        tutorDao.delete(s);

    }
}
