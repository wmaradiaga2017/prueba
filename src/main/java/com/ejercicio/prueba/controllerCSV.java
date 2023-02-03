/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.prueba;

import com.ejercicio.dao.LecturaCsv;
import com.ejercicio.dao.ResponseMessage;
import com.ejercicio.interfaces.FilesStorageService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author walter.maradiaga
 */
@CrossOrigin()
@RestController
@RequestMapping("/archivo/csv")
@Slf4j
public class controllerCSV {

    @Autowired
    CSVService fileService;

    @Autowired
    FilesStorageService storageService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (LecturaCsv.hasCSVFormat(file)) {
            
            try {
                //volcar archivo a BD    
                fileService.guardar(file);
                //Guarda archivo 
                storageService.guardararchivo(file);

                message = "El archivo se volco y creo exitosamente: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Ocurrio un problema con el archivo: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Suba un archivo con el formato correcto.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}
