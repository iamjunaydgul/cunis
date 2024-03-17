package com.project.uniInfo.service;

import com.project.uniInfo.entity.University;
import com.project.uniInfo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getByCountry(String country) {
        return universityRepository.findByCountry(country);
    }

    public void saveUniversity(University university) {
        universityRepository.save(university);
    }

    public List<University> getUniversitiesByCountry(String country) {
        return universityRepository.findByCountry(country);
    }

    public University updateUniversity(String country, Long id, University updatedUniversity) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            if (university.getCountry().equalsIgnoreCase(country)) {
                university.setName(updatedUniversity.getName());
                university.setDomains(updatedUniversity.getDomains());
                university.setAlpha_two_code(updatedUniversity.getAlpha_two_code());
                university.setWeb_pages(updatedUniversity.getWeb_pages());
                university.setStateProvince(updatedUniversity.getStateProvince());
                university.setCountry(updatedUniversity.getCountry());
                return universityRepository.save(university);
            } else {
                throw new IllegalArgumentException("University not found in the specified country");
            }
        } else {
            throw new IllegalArgumentException("University not found with ID: " + id);
        }
    }
}
