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
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("archivos");

    @Override
    public void creardirectorio(String ruta) {
        if (!new File(ruta).exists()) {
            new File(ruta).mkdir();
        }
    }

    @Override
    public void guardararchivo(MultipartFile file, String ruta) {

        try {
            String orgName = file.getOriginalFilename();
            String filePath = ruta + orgName;
            File dest = new File(filePath);
            file.transferTo(dest);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("No se guardo nuevamente por que ya existe el archivo.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

}
