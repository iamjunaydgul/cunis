package com.project.uniInfo.repository;

import com.project.uniInfo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByCountry(String country);
}
