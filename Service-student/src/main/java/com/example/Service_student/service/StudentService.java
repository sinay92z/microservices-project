package com.example.Service_student.service;

import com.example.Service_student.dto.StudentDTO;
import com.example.Service_student.entity.Student;
import com.example.Service_student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(String id) {
        return repository.findById(id).orElse(null);
    }

    public StudentDTO getSchoolByStudentId(String studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            String schoolServiceUrl = "http://localhost:8082/schools/" + student.getSchoolId();
            return restTemplate.getForObject(schoolServiceUrl, StudentDTO.class);
        }
        return null;
    }
    public void deleteStudent(String id) {
        repository.deleteById(id);
    }
}



