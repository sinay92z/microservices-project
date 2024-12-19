package com.example.Service_student.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "students")
public class Student {

    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private Long schoolId;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
    // Builder method
    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private String id;
        private String name;
        private int age;
        private String gender;
        private Long schoolId;

        public StudentBuilder id(String id) {
            this.id = id;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder age(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public StudentBuilder schoolId(Long schoolId) {
            this.schoolId = schoolId;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.setId(this.id);
            student.setName(this.name);
            student.setAge(this.age);
            student.setGender(this.gender);
            student.setSchoolId(this.schoolId);
            return student;
        }
    }
}
