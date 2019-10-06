package io.midas.jdbc;

import io.midas.model.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StudentDaoTest {
    private StudentDao studentDao;

    @Test
    public void getStudentsTest()
    {
        studentDao = new StudentDao();
        List<Student> students = studentDao.getStudents();
        int expectedNum = 1;
        Assert.assertEquals(expectedNum,students.size());
    }




    @Before
    public void setUp()
    {
        studentDao = new StudentDao();
        Student s = new Student();
        s.setName("testStudent");
        s.setEmail("111@gmail.com");
        studentDao.save(s);
    }

    @After
    public  void tearDown()
    {
        studentDao = new StudentDao();
        Student s = new Student();
        s.setName("testStudent");
        s.setEmail("111@gmail.com");
        studentDao.delete(s);

    }
}
