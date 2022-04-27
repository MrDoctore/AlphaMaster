package com.alphaproject.util;

import com.alphaproject.model.School;

public class SchoolCreator {

    public static School createSchoolToBeSaved() {
        School school = new School();
        school.setName("School Test");
        school.setAddress("School address Test");
        school.setEmail("schoolemailtest@test.com");
        school.setLogo("schoolLogoTest.jpg");
        return school;
    }

    public static School createValidSchool() {
        School school = new School();
        school.setId(1L);
        school.setName("School Test");
        school.setAddress("School address Test");
        school.setEmail("schoolemailtest@test.com");
        school.setLogo("schoolLogoTest.jpg");
        return school;
    }

    public static School createValidUpdateSchool() {
        School school = new School();
        school.setId(1L);
        school.setName("School Test update");
        school.setAddress("School address Test update");
        school.setEmail("schoolemailtestupdate@test.com");
        school.setLogo("schoolLogoTestupdate.jpg");
        return school;
    }
}
