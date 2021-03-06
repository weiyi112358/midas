package io.midas.service;

import io.midas.ApplicationBoot;
import io.midas.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    private Student student;
    
    @Before
    public void init(){
        student = new Student();
        student.setEmail("ywei729@gwu.edu");
        student.setName("yi wei");
    }

    @Test
    public void save(){
        studentService.save(student);
    }


}
