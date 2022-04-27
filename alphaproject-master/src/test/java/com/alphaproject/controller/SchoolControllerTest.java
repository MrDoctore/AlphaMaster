package com.alphaproject.controller;

import com.alphaproject.model.School;
import com.alphaproject.requests.school.SchoolPostRequestBody;
import com.alphaproject.requests.school.SchoolPutRequestBody;
import com.alphaproject.service.SchoolService;
import com.alphaproject.util.SchoolCreator;
import com.alphaproject.util.SchoolPostRequestBodyCreator;
import com.alphaproject.util.SchoolPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SchoolControllerTest {
    @InjectMocks //test class
    private SchoolController schoolController;

    @Mock
    private SchoolService schoolServiceMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(schoolServiceMock.listAll())
                .thenReturn(List.of(SchoolCreator.createValidSchool()));

        BDDMockito.when(schoolServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(SchoolCreator.createValidSchool());

        BDDMockito.when(schoolServiceMock.save(ArgumentMatchers.any(SchoolPostRequestBody.class)))
                .thenReturn(SchoolCreator.createValidSchool());

        BDDMockito.doNothing().when(schoolServiceMock).replace(ArgumentMatchers.any(SchoolPutRequestBody.class));

        BDDMockito.doNothing().when(schoolServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll returns list of schools when successful")
    void listAll_ReturnsListOfSchools_WhenSuccessful(){
        String expectedName = SchoolCreator.createValidSchool().getName();

        List<School> schools = schoolController.list().getBody();

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

        School school = schoolController.findById(1).getBody();

        Assertions.assertThat(school).isNotNull();

        Assertions.assertThat(school.getId()).isNotNull().isEqualTo(expectedId);
    }


    @Test
    @DisplayName("save returns school when successful")
    void save_ReturnsSchool_WhenSuccessful(){

        School school = schoolController.save(SchoolPostRequestBodyCreator.createSchoolPostRequestBody()).getBody();

        Assertions.assertThat(school).isNotNull().isEqualTo(SchoolCreator.createValidSchool());

    }

    @Test
    @DisplayName("replace updates school when successful")
    void replace_UpdatesSchool_WhenSuccessful(){

        Assertions.assertThatCode(() ->schoolController.replace(SchoolPutRequestBodyCreator.createSchoolPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<School> entity = schoolController.replace(SchoolPutRequestBodyCreator.createSchoolPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("delete removes school when successful")
    void delete_RemovesSchool_WhenSuccessful(){

        Assertions.assertThatCode(() ->schoolController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = schoolController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}