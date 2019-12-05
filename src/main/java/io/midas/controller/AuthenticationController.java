package io.midas.controller;

import io.midas.model.Student;
import io.midas.service.StudentService;
import io.midas.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;



@RestController
@RequestMapping(value = {"/auth"})
public class AuthenticationController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String authenticate(@RequestBody Student student)
    {
        String token = "";

        try{
            Student s = studentService.getStudentByCredentials(student.getEmail(),student.getPassword());
            if(s==null)
            {
                return "error";
            }
            token = JwtUtil.generateToken(student);

        }
        catch (Exception e)
        {
            String msg = e.getMessage();
            if(msg==null) msg="BAD REQUEST";
            return msg;


        }



        return token;

    }

}
