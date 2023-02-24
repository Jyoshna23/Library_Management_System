package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){

        return studentService.createStudent(student);
    }

    @GetMapping("/get_name")
    public String findByEmail(@RequestParam String email){
        return studentService.findByEmail(email);
    }

    @PutMapping("/update")
    public String updateMobNo(@RequestBody StudentUpdateMobRequestDto studentReq){
        return studentService.updateMobNo(studentReq);
    }
}
