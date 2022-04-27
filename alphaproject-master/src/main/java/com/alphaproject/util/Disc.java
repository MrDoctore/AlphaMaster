package com.alphaproject.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Disc {
    public String path;

    @Value("${school.disc.raiz}")
    private String raiz;

    @Value("${school.disc.directory-answers}")
    private String answersDirectory;

    @Value("${school.disc.directory-logos}")
    private String logosDirectory;


    @Value("${school.disc.directory-students}")
    private String studentsDirectory;

    public void saveLogo(MultipartFile logo) {
        this.save(this.logosDirectory, logo);
    }


    public void saveStudents(MultipartFile students) {
        this.save(this.studentsDirectory, students);
    }


    public void salveAnswers(MultipartFile answers) {
        this.save(this.answersDirectory, answers);
    }

    public void save(String directory, MultipartFile file) {
        Path directoryPath = Paths.get(this.raiz, directory);
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(directoryPath);
            file.transferTo(filePath.toFile());
            this.path = raiz + directory + "/" + file.getOriginalFilename();


        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }

}
