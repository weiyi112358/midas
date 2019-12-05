package io.midas.repository;

import io.midas.ApplicationBoot;
import io.midas.model.Organization;
import io.midas.model.Role;
import io.midas.model.Student;
import io.midas.model.Tutor;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class StudentDaoTest {
    private static StudentDao studentDao;
    private static RoleDao roleDao;
    private static OrganizationDao organizationDao;

    @Autowired
    TutorDao tutorDao;


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    List<Role> roles = new ArrayList<>();


    @BeforeClass
    public static void init(){
        studentDao = new StudentDaoImpl();
        roleDao = new RoleDaoImpl();
        organizationDao = new OrganizationDaoImpl();
    }

    @Before
    public void setUp()
    {
        Student s = new Student();
        s.setName("test");
        s.setEmail("yiwei@gmail.com");
        roles.add(roleDao.getRoleByName("Manager"));
        s.setRoles(roles);

        Organization o = organizationDao.getOrganizationByName("george washington university");

        s.setOrganization(o);

        studentDao.save(s);

    }

    @Test
    public void getStudents()
    {
        Tutor s = new Tutor();
        s.setName("test");
        s.setEmail("yiwei@gmail.com");
        tutorDao.save(s);
        List<Student> students = studentDao.getStudents();
        int expected = 2;
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

    @Test
    public void getStudentWithRoleTest()
    {
        Student student = new Student();
        student.setRoles(roles);
        //roles.add(roleDao.getRoleByName("user"));
        student.setName("test2");
        student.setEmail("1111");
        student.setPassword("123");
        student.setSecretKey("adddd");
        Organization o = organizationDao.getOrganizationByName("george washington university");
        student.setOrganization(o);
        studentDao.save(student);
        Student result = studentDao.getStudentByName(student.getName());
        assertEquals(result.getRoles().size(),roles.size());

    }

    @After
    public void delete()
    {
        studentDao.delete("test");
        studentDao.delete("test2");
    }

}
