package io.midas.jdbc;


import io.midas.model.Tutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TutorDao {

    static final String DB_URL = "jdbc:postgresql://localhost:5431/midas";
    static final String USER = "admin";
    static final String PASS = "password";
    private final Logger logger = LoggerFactory.getLogger((getClass()));

    public List<Tutor> getTutors()
    {
        Statement stmt = null;
        ResultSet rs = null;
        List<Tutor> tutors = new ArrayList<>();
        logger.debug("start: tutorList");

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tutor";
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                //String email = rs.getString("email");

                Tutor tutor = new Tutor();
                tutor.setId(id);
                tutor.setName(name);


                tutors.add(tutor);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  tutors;
    }

    public  boolean save(Tutor s)
    {
        Statement stmt = null;
        //ResultSet rs = null;
        //Student student = new Student();
        logger.debug("Start: save");

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "insert into tutor (name) values('"+s.getName()+"')";

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

    public boolean delete(Tutor s)
    {
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "delete from tutor where name = '"+s.getName()+"'";
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
}
