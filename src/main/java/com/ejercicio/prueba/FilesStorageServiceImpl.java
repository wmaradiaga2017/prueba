/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.prueba;

import com.ejercicio.interfaces.FilesStorageService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author walter.maradiaga
 */
@Service
@Slf4j
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("archivos");

    @Override
    public void creardirectorio() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Problemas al crear el directorio");
        }
    }

    @Override
    public void guardararchivo(MultipartFile file) {

        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("No se guardo nuevamente por que ya existe el archivo.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

}
