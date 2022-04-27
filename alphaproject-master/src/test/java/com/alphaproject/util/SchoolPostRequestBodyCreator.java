package com.alphaproject.util;
import com.alphaproject.requests.school.SchoolPostRequestBody;

public class SchoolPostRequestBodyCreator {
    public static SchoolPostRequestBody createSchoolPostRequestBody(){
        SchoolPostRequestBody schoolPostRequestBody = new SchoolPostRequestBody();
        schoolPostRequestBody.setName(SchoolCreator.createSchoolToBeSaved().getName());
        schoolPostRequestBody.setAddress(SchoolCreator.createSchoolToBeSaved().getAddress());
        schoolPostRequestBody.setEmail(SchoolCreator.createSchoolToBeSaved().getEmail());
        schoolPostRequestBody.setLogo(SchoolCreator.createSchoolToBeSaved().getLogo());
        return schoolPostRequestBody;
    }
}
