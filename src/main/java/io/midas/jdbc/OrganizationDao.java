package io.midas.jdbc;

import io.midas.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5431/midas";
    static final String USER = "admin";
    static final String PASS = "password";
    private final Logger logger = LoggerFactory.getLogger((getClass()));

    public List<Organization> getOrganizations()
    {
        Statement stmt = null;
        ResultSet rs = null;
        List<Organization> organizations = new ArrayList<>();
        logger.debug("start: OrganizationList");

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM organization";
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Organization organization = new Organization();
                //organization.setId(id);
                organization.setName(name);
                organizations.add(organization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  organizations;
    }

    public  boolean save(Organization s)
    {
        Statement stmt = null;
        //ResultSet rs = null;
        //Student student = new Student();
        logger.debug("Start: save");

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "insert into organization (name) values('"+s.getName()+"')";

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

    public boolean delete(Organization s)
    {
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "delete from organization where name = '"+s.getName()+"'";
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
