package io.midas.controller;


import io.midas.model.Student;
import io.midas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/student"})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/paths",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloWorld(@RequestBody Student student){
        String a = "aaa";
        return "HelloWorld!";
    }

}
