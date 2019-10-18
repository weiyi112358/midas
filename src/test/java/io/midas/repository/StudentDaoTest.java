package io.midas.repository;

import io.midas.model.Student;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StudentDaoTest {
    private static StudentDao studentDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init(){
        studentDao = new StudentDaoImpl();
    }

    @Before
    public void setUp()
    {
        Student s = new Student();
        s.setName("test");
        s.setEmail("yiwei@gmail.com");
        studentDao.save(s);

    }

    @Test
    public void getStudents()
    {
        List<Student> students = studentDao.getStudents();
        int expected = 1;
        Assert.assertEquals(expected,students.size());
    }



    @Test
    public void update()
    {


        Student res = studentDao.getStudentByName("test");
        res.setEmail("update@email.com");
        studentDao.update(res);

        Student newRes = studentDao.getStudentByName("test");

        Assert.assertEquals(newRes.getEmail(),"update@email.com");
    }

    @After
    public void delete()
    {
        studentDao.delete("test");
    }

}
