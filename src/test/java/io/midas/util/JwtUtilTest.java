package io.midas.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.midas.model.Role;
import io.midas.model.Student;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;

public class JwtUtilTest {
    @Test
    public void generateTokenTest(){
        Student s = new Student();
        s.setName("yiwei");
        s.setId(100L);
        Role r = new Role();
        r.setAllowedCreate(true);
        r.setAllowedDelete(true);

        r.setAllowedResource("/student");
        List<Role> roles = new ArrayList<Role>();

        roles.add(r);
        s.setRoles(roles);
        String token = JwtUtil.generateToken(s);
        System.out.println(token);

        Claims actualResult = JwtUtil.decodeJwtToken(token);

        Assert.assertEquals(actualResult.getId(),String.valueOf((s.getId())));

    }



}
