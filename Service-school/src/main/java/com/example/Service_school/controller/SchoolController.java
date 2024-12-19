package com.example.Service_school.controller;

import com.example.Service_school.dto.SchoolDTO;
import com.example.Service_school.repository.SchoolRepository;
import com.example.Service_school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    // POST: Ajouter une école
    @PostMapping
    public ResponseEntity<SchoolDTO> createSchool(@RequestBody SchoolDTO schoolDTO) {
        SchoolDTO createdSchool = schoolService.createSchool(schoolDTO);
        return ResponseEntity.ok(createdSchool);
    }

    // GET: Récupérer toutes les écoles
    @GetMapping
    public ResponseEntity<List<SchoolDTO>> getAllSchools() {
        List<SchoolDTO> schools = schoolService.getAllSchools();
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDTO> getSchoolById(@PathVariable Long id) {
        SchoolDTO schoolDTO = schoolService.getSchoolById(id);
        return schoolDTO != null ? ResponseEntity.ok(schoolDTO) : ResponseEntity.notFound().build();
    }


    // DELETE: Supprimer une écôle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        boolean deleted = schoolService.deleteSchool(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<SchoolDTO> updateSchool(@PathVariable Long id, @RequestBody SchoolDTO updatedSchoolDTO) {
        SchoolDTO updatedSchool = schoolService.updateSchool(id, updatedSchoolDTO);
        return updatedSchool != null ? ResponseEntity.ok(updatedSchool) : ResponseEntity.notFound().build();
    }

}
