package com.project.uniInfo.controller;

import com.project.uniInfo.entity.PersistUniData;
import com.project.uniInfo.entity.University;
import com.project.uniInfo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @GetMapping
    public List<University> getByCountry(@RequestParam String country) {
        return universityService.getByCountry(country);
    }

    @PutMapping
    public University updateUniversity(
            @RequestParam String country,
            @RequestParam Long id,
            @RequestBody University university) {
        return universityService.updateUniversity(country, id, university);
    }
    @PostMapping
    public ResponseEntity<String> saveUniversity(@RequestBody PersistUniData data) {

        for (University university : data.getUniversities()) {
            universityService.saveUniversity(university);
        }
        return ResponseEntity.status(HttpStatus.OK).body("All universities saved successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // for uncaught exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
