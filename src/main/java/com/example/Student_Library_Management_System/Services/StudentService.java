package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public String createStudent(Student student){


        //setting the attributes of the card class
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        //setting the attributes of student

        student.setCard(card);


        //If we are using unidirectional, we need to save both the card and student
        //studentRepository.save() and cardRepository.save()
        //But as we are using bidirectional mapping, the child entity will be automatically saved through
        // cascading effect

        studentRepository.save(student);

        return "Student and Card added successfully";
    }

    public String findByEmail(String email){

        //Here I am fetching the student entity
        Student student = studentRepository.findByEmail(email);

        //then calling the name of the student through getName
        return student.getName();
    }


    public String updateMobNo(StudentUpdateMobRequestDto studentReq){
        //Generally .save function will work in two ways.
        //If we have don't have primary key then it will work as save/create function
        //If PK is already present, then it will act as update function.

        //BUt the problem with it is, if we directly call the update function with id, it update the mobNo
        //and all the other attributes will become null.
        //SO inorder to avoid this, we first fetch the original student details and update the particular attribute
        //which we want to change/update.



        // We need to convert the Dto ---> entity to save better


        Student originalStudent = studentRepository.findById(studentReq.getId()).get();

        originalStudent.setMobileNo(studentReq.getMobNo());

         studentRepository.save(originalStudent);

         return "Mobile number updated successfully.";

    }


}
