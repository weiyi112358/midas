package io.midas.repository;

import io.midas.model.Student;

import java.util.List;

public interface StudentDao {

    boolean save(Student student);
    boolean update (Student student);
    //TODO delete(Student stu)
    boolean delete(String studentName);
    Student getStudentByName(String studentName);
    Student getStudentByCredentials(String email,String password);
    List<Student> getStudents();
}
