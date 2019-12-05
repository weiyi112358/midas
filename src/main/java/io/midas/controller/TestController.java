package io.midas.controller;

import io.midas.jdbc.StudentDao;
import io.midas.model.Student;
import io.midas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;


@RestController
@RequestMapping(value = {"/test"})
public class TestController {

//    @Autowired
//    private StudentService studentService;

    StudentDao dao = new StudentDao();

    @RequestMapping(value = "/paths", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String helloWorld(){
        return "HelloWorld!";
    }


    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Student> getAllStudents()
    {
        return dao.getStudents();
    }

    @RequestMapping(value = "/path/{pathValue1}/object/{pathValue2}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getPath(@PathVariable(name = "pathValue1") String p1, @PathVariable(name = "pathValue2") String p2)
    {
        return p1+","+p2;
    }

    @RequestMapping(value = "/path/student",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Student getStudentsById(@RequestParam(name = "studentId") int id)
    {
        Student res = dao.getStudentById(id);
        return res;
    }




}
