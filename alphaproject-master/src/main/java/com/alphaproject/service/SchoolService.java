package com.alphaproject.service;

import com.alphaproject.exceptions.BadRequestException;
import com.alphaproject.model.School;
import com.alphaproject.repository.SchoolRepository;
import com.alphaproject.requests.school.SchoolPostRequestBody;
import com.alphaproject.requests.school.SchoolPutRequestBody;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> listAll(){
        return schoolRepository.findAll();
    }

    public School findByIdOrThrowBadRequestException(long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Escola não encontrada"));
    }
    //@Transactional
    public School save(SchoolPostRequestBody schoolPostRequestBody){
        School school = new School();
        school.setName(schoolPostRequestBody.getName());
        school.setAddress(schoolPostRequestBody.getAddress());
        school.setEmail(schoolPostRequestBody.getEmail());
        school.setLogo(schoolPostRequestBody.getLogo());
        return schoolRepository.save(school);
    }

    public void replace(SchoolPutRequestBody schoolPutRequestBody){
        School school = findByIdOrThrowBadRequestException(schoolPutRequestBody.getId());
        school.setName(schoolPutRequestBody.getName());
        school.setAddress(schoolPutRequestBody.getAddress());
        school.setEmail(schoolPutRequestBody.getEmail());
        school.setLogo(schoolPutRequestBody.getLogo());
        schoolRepository.save(school);
    }

    public void delete(long id){
        schoolRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
