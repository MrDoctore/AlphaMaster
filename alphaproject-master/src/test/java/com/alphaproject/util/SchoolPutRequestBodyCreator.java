package com.alphaproject.util;

import com.alphaproject.requests.school.SchoolPutRequestBody;

public class SchoolPutRequestBodyCreator {
    public static SchoolPutRequestBody createSchoolPutRequestBody(){
        SchoolPutRequestBody schoolPutRequestBody = new SchoolPutRequestBody();
        schoolPutRequestBody.setId(SchoolCreator.createValidUpdateSchool().getId());
        schoolPutRequestBody.setName(SchoolCreator.createValidUpdateSchool().getName());
        schoolPutRequestBody.setAddress(SchoolCreator.createValidUpdateSchool().getAddress());
        schoolPutRequestBody.setEmail(SchoolCreator.createValidUpdateSchool().getEmail());
        schoolPutRequestBody.setLogo(SchoolCreator.createValidUpdateSchool().getLogo());
        return schoolPutRequestBody;
    }
}
