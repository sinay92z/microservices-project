package com.example.Service_student.dto;


public class StudentDTO {
    private String id;
    private String name;
    private int age;
    private String gender;
    private Long schoolId;

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
    public static StudentDTOBuilder builder() {
        return new StudentDTOBuilder();
    }

    public static class StudentDTOBuilder {
        private String id;
        private String name;
        private int age;
        private String gender;
        private Long schoolId;

        public StudentDTOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public StudentDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentDTOBuilder age(int age) {
            this.age = age;
            return this;
        }

        public StudentDTOBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public StudentDTOBuilder schoolId(Long schoolId) {
            this.schoolId = schoolId;
            return this;
        }

        public StudentDTO build() {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(this.id);
            studentDTO.setName(this.name);
            studentDTO.setAge(this.age);
            studentDTO.setGender(this.gender);
            studentDTO.setSchoolId(this.schoolId);
            return studentDTO;
        }
    }
}
