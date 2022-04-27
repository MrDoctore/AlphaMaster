package com.alphaproject.repository;

import com.alphaproject.model.School;
import com.alphaproject.util.SchoolCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest

@DisplayName("Tests for school repository")
class SchoolRepositoryTest {
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    @DisplayName("Save creates school when successful")
    void save_PersistSchool_WhenSuccessful() {
        School schoolToBeSaved = SchoolCreator.createSchoolToBeSaved();
        School savedSchool = this.schoolRepository.save(schoolToBeSaved);
        Assertions.assertThat(savedSchool).isNotNull();
        Assertions.assertThat(savedSchool.getId()).isNotNull();
        Assertions.assertThat(savedSchool.getName()).isEqualTo(schoolToBeSaved.getName());
        Assertions.assertThat(savedSchool.getAddress()).isEqualTo(schoolToBeSaved.getAddress());
        Assertions.assertThat(savedSchool.getEmail()).isEqualTo(schoolToBeSaved.getEmail());
        Assertions.assertThat(savedSchool.getLogo()).isEqualTo(schoolToBeSaved.getLogo());
    }

    @Test
    @DisplayName("Save update school when successful")
    void save_UpdateSchool_WhenSuccessful() {
        School schoolToBeSaved = SchoolCreator.createSchoolToBeSaved();
        School savedSchool = this.schoolRepository.save(schoolToBeSaved);
        savedSchool.setName("School Update Name");
        savedSchool.setAddress("School Update Address");
        savedSchool.setEmail("School Update Email");
        savedSchool.setLogo("School Update Logo");
        School updatedSchool = this.schoolRepository.save(savedSchool);
        Assertions.assertThat(updatedSchool).isNotNull();
        Assertions.assertThat(updatedSchool.getId()).isNotNull();
        Assertions.assertThat(updatedSchool.getName()).isEqualTo(savedSchool.getName());
        Assertions.assertThat(updatedSchool.getAddress()).isEqualTo(savedSchool.getAddress());
        Assertions.assertThat(updatedSchool.getEmail()).isEqualTo(savedSchool.getEmail());
        Assertions.assertThat(updatedSchool.getLogo()).isEqualTo(savedSchool.getLogo());
    }

    @Test
    @DisplayName("Delete removes school when successful")
    void delete_RemovesSchool_WhenSuccessful() {
        School schoolToBeSaved = SchoolCreator.createSchoolToBeSaved();
        School savedSchool = this.schoolRepository.save(schoolToBeSaved);
        this.schoolRepository.delete(savedSchool);
        Optional<School> schoolOptional = this.schoolRepository.findById(savedSchool.getId());
        System.out.println(schoolOptional);
        Assertions.assertThat(schoolOptional).isEmpty();
    }
}