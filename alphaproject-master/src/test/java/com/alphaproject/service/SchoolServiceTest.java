package com.alphaproject.service;

import com.alphaproject.model.School;
import com.alphaproject.repository.SchoolRepository;
import com.alphaproject.util.SchoolCreator;
import com.alphaproject.util.SchoolPostRequestBodyCreator;
import com.alphaproject.util.SchoolPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
@ExtendWith(SpringExtension.class)
class SchoolServiceTest {

    @InjectMocks //test class
    private SchoolService schoolService;

    @Mock
    private SchoolRepository schoolRepositoryMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(schoolRepositoryMock.findAll())
                .thenReturn(List.of(SchoolCreator.createValidSchool()));
        BDDMockito.when(schoolRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(SchoolCreator.createValidSchool()));
        BDDMockito.when(schoolRepositoryMock.save(ArgumentMatchers.any(School.class)))
                .thenReturn(SchoolCreator.createValidSchool());
        BDDMockito.doNothing().when(schoolRepositoryMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("listAll returns list of schools when successful")
    void listAll_ReturnsListOfSchools_WhenSuccessful(){
        String expectedName = SchoolCreator.createValidSchool().getName();
        List<School> schools = schoolService.listAll();
        Assertions.assertThat(schools)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(schools.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns school when successful")
    void findById_ReturnsSchool_WhenSuccessful(){
        Long expectedId = SchoolCreator.createValidSchool().getId();
        School school = schoolService.findByIdOrThrowBadRequestException(1);
        Assertions.assertThat(school).isNotNull();
        Assertions.assertThat(school.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("save returns school when successful")
    void save_ReturnsSchool_WhenSuccessful(){
        School school = schoolService.save(SchoolPostRequestBodyCreator.createSchoolPostRequestBody());
        Assertions.assertThat(school).isNotNull().isEqualTo(SchoolCreator.createValidSchool());
    }

    @Test
    @DisplayName("replace updates school when successful")
    void replace_UpdatesSchool_WhenSuccessful(){
        Assertions.assertThatCode(() ->schoolService.replace(SchoolPutRequestBodyCreator.createSchoolPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes school when successful")
    void delete_RemovesSchool_WhenSuccessful(){
        Assertions.assertThatCode(() ->schoolService.delete(1))
                .doesNotThrowAnyException();
    }
}