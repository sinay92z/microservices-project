package com.example.Service_school.service;

import com.example.Service_school.dto.SchoolDTO;
import com.example.Service_school.entity.School;
import com.example.Service_school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository repository;

    // Récupérer toutes les écoles
    public List<SchoolDTO> getAllSchools() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Récupérer une école par ID
    public SchoolDTO getSchoolById(Long id) {
        Optional<School> school = repository.findById(id);
        return school.map(this::mapToDTO).orElse(null);
    }

    // Ajouter une école
    public SchoolDTO createSchool(SchoolDTO schoolDTO) {
        School school = mapToEntity(schoolDTO);
        School savedSchool = repository.save(school);
        return mapToDTO(savedSchool);
    }

    // Mettre à jour une école
    public SchoolDTO updateSchool(Long id, SchoolDTO updatedSchoolDTO) {
        Optional<School> schoolOptional = repository.findById(id);
        if (schoolOptional.isPresent()) {
            School school = schoolOptional.get();
            school.setName(updatedSchoolDTO.getName());
            School updatedSchool = repository.save(school);
            return mapToDTO(updatedSchool);
        }
        return null;
    }

    // Supprimer une école
    public boolean deleteSchool(Long id) {
        Optional<School> schoolOptional = repository.findById(id);
        if (schoolOptional.isPresent()) {
            repository.delete(schoolOptional.get());
            return true;
        }
        return false;
    }

    // Mapper School vers SchoolDTO
    private SchoolDTO mapToDTO(School school) {
        SchoolDTO dto = new SchoolDTO();
        dto.setId(school.getId());
        dto.setName(school.getName());
        return dto;
    }

    // Mapper SchoolDTO vers School
    private School mapToEntity(SchoolDTO schoolDTO) {
        School school = new School();
        school.setId(schoolDTO.getId());
        school.setName(schoolDTO.getName());
        return school;
    }
}
