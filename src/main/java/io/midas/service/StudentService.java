package io.midas.service;

import io.midas.model.Student;
import io.midas.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao dao;

    public Student save(Student s)
    {
        dao.save(s);
        return s;
    }

    public List<Student> getAllStudents()
    {
        return dao.getStudents();
    }

    public Student getStudentByCredentials(String email,String password)
    {
        return dao.getStudentByCredentials(email,password);

    }
}
