package com.alphaproject.controller;

import com.alphaproject.model.School;
import com.alphaproject.requests.school.SchoolPostRequestBody;
import com.alphaproject.requests.school.SchoolPutRequestBody;
import com.alphaproject.service.SchoolService;
import com.alphaproject.util.Disc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("escolas")
public class SchoolController {
    private final Disc disc;
    private final SchoolService schoolService;

    public SchoolController(Disc disc, SchoolService schoolService) {
        this.disc = disc;
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<School>> list() {
        return ResponseEntity.ok(schoolService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<School> findById(@PathVariable long id){
        return ResponseEntity.ok(schoolService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<School> save(@RequestBody @Valid SchoolPostRequestBody schoolPostRequestBody){
        return new ResponseEntity<>(schoolService.save(schoolPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<School> replace(@RequestBody @Valid SchoolPutRequestBody schoolPutRequestBody){
        schoolService.replace(schoolPutRequestBody);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        schoolService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
