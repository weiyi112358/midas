package io.midas.jdbc;

import io.midas.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5431/midas";
    static final String USER = "admin";
    static final String PASS = "password";
    private final Logger logger = LoggerFactory.getLogger((getClass()));

    public List<Student> getStudents()
    {
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        logger.debug("start: studentList");

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM students";
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setEmail(email);

                students.add(student);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  students;
    }

    public  boolean save(Student s)
    {
        Statement stmt = null;
        //ResultSet rs = null;
        //Student student = new Student();
        logger.debug("Start: save");

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "insert into student (name, email) values('"+s.getName()+"','"+s.getEmail()+"')";

            int i = stmt.executeUpdate(sql);
            if(i==1)
            {
                logger.debug("Created new record");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(Student s)
    {
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "delete from student where name = '"+s.getName()+"'";
            int i = stmt.executeUpdate(sql);
            if(i==1)
            {
                logger.debug("Deleted a record");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public Student getStudentById(int sid)
    {
        Statement stmt = null;
        ResultSet rs = null;

        logger.debug("start: studentList");
        Student student = new Student();
        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "select * from students where id = '"+sid+"'";
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");


                student.setId(id);
                student.setName(name);
                student.setEmail(email);



            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  student;


    }



}
