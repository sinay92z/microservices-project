package com.example.Service_student.controller;

import com.example.Service_student.dto.SchoolDTO;
import com.example.Service_student.dto.StudentDTO;
import com.example.Service_student.entity.Student;
import com.example.Service_student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String SCHOOL_SERVICE_URL = "http://service-school/api/schools/";

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = mapToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(mapToDTO(savedStudent));
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentWithSchool(@PathVariable String id) {
        return studentRepository.findById(id)
                .map(student -> {
                    SchoolDTO school = restTemplate.getForObject(SCHOOL_SERVICE_URL + student.getSchoolId(), SchoolDTO.class);
                    StudentDTO studentDTO = mapToDTO(student);
                    if (school != null) {
                        studentDTO.setName(studentDTO.getName() + " - School: " + school.getName());
                    }
                    return ResponseEntity.ok(studentDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO updatedStudentDTO) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudentDTO.getName());
                    student.setAge(updatedStudentDTO.getAge());
                    student.setGender(updatedStudentDTO.getGender());
                    student.setSchoolId(updatedStudentDTO.getSchoolId());
                    Student updatedStudent = studentRepository.save(student);
                    return ResponseEntity.ok(mapToDTO(updatedStudent));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    private StudentDTO mapToDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .gender(student.getGender())
                .schoolId(student.getSchoolId())
                .build();
    }
    private Student mapToEntity(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.getId())
                .name(studentDTO.getName())
                .age(studentDTO.getAge())
                .gender(studentDTO.getGender())
                .schoolId(studentDTO.getSchoolId())
                .build();
    }
}
