package io.midas.repository;

import io.midas.ApplicationBoot;
import io.midas.model.Tutor;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class TutorDaoTest {
    @Autowired
    TutorDao tutorDao;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        int expected = 1;
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
