/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.prueba;

import com.ejercicio.interfaces.FilesStorageService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    private final File path = new File("src/main/resources/csv/");

    @Override
    public void guardararchivo(MultipartFile file) {

        try {
            Path uploadPath = Paths.get("Archivos");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = file.getInputStream()) { 
                Path filePath = uploadPath.resolve(file.getOriginalFilename());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {       
                 throw new IOException("No se puede guardar", ioe);
            }
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("No se guardo nuevamente por que ya existe el archivo.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

}
