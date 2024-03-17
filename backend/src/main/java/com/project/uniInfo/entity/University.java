package com.project.uniInfo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String[] domains;
    private String alpha_two_code;
    private String[] web_pages;
    @JsonProperty("state-province")
    @Column(nullable = false)
    private String stateProvince;
    private String country;

    @PrePersist
    @PreUpdate
    public void handleNullFields() {
        // Set empty string for field that are null
        if (stateProvince == null) {
            stateProvince = "";
        }
    }
}
