package io.midas.repository;

import io.midas.model.Tutor;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TutorDaoTest {
    private static TutorDao tutorDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init(){
        tutorDao = new TutorDaoImpl();
    }

    @Before
    public void setUp()
    {
        Tutor s = new Tutor();
        s.setName("test");
        s.setEmail("yiwei@gmail.com");
        tutorDao.save(s);

    }

    @Test
    public void getTutors()
    {
        List<Tutor> tutors = tutorDao.getTutors();
        int expected = 2;
        Assert.assertEquals(expected,tutors.size());
    }



    @Test
    public void update()
    {


        Tutor res = tutorDao.getTutorByName("test");
        res.setEmail("update@email.com");
        tutorDao.update(res);

        Tutor newRes = tutorDao.getTutorByName("test");

        Assert.assertEquals(newRes.getEmail(),"update@email.com");
    }

    @After
    public void delete()
    {
        tutorDao.delete("test");
    }

}
