package com.example.Service_student.dto;

import lombok.Data;

@Data
public class SchoolDTO {
    private Long id;
    private String name;
    private String address;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
