package com.project.uniInfo.service;

import com.project.uniInfo.entity.University;
import com.project.uniInfo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getByCountry(String country) {
        return universityRepository.findByCountry(country);
    }

    public List<University> getByCountryAndState(String country, String stateProvince) {
        List<University> universities= universityRepository.findByCountry(country);
        List<University> countryProvinceList= new ArrayList<>();
        universities.forEach(it-> {
            if(it.getCountry().equals(country) && it.getStateProvince().equals(stateProvince)){
                countryProvinceList.add(it);
            }
        });
        return countryProvinceList;
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
                if(updatedUniversity.getName()!= null){
                    university.setName(updatedUniversity.getName());
                }
                if(updatedUniversity.getDomains()!= null){
                    university.setDomains(updatedUniversity.getDomains());
                }
                if(updatedUniversity.getAlpha_two_code()!= null){
                    university.setAlpha_two_code(updatedUniversity.getAlpha_two_code());
                }
                if(updatedUniversity.getWeb_pages()!= null){
                    university.setWeb_pages(updatedUniversity.getWeb_pages());
                }
                if(updatedUniversity.getStateProvince()!= null) {
                    university.setStateProvince(updatedUniversity.getStateProvince());
                }
                if(updatedUniversity.getCountry()!= null) {
                    university.setCountry(updatedUniversity.getCountry());
                }
                return universityRepository.save(university);
            } else {
                throw new IllegalArgumentException("University not found in the specified country");
            }
        } else {
            throw new IllegalArgumentException("University not found with ID: " + id);
        }
    }
}
