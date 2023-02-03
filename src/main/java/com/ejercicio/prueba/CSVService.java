/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.prueba;

import com.ejercicio.repository.Archivo;
import com.ejercicio.dao.LecturaCsv;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ejercicio.repository.ArchivoRepository;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author walter.maradiaga
 */
@Service
@Slf4j
public class CSVService {

    @Autowired
    private ArchivoRepository repository;

    public void guardar(MultipartFile file) {
        log.info("LLega archivo");
        try {
            int lista = recorrerCSV(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Falla en la informacion de la data: " + e.getMessage());
        }
    }

    public List<Archivo> getAllTutorials() {
        return repository.findAll();
    }

    public int recorrerCSV(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Archivo> lista = new ArrayList<Archivo>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            int c = 0;
            for (CSVRecord record : csvRecords) {
                Archivo fila = new Archivo(
                        Long.parseLong(record.get(0)),
                        record.get(1),
                        record.get(2),
                        record.get(3),
                        record.get(4), record.get(5));
                c++;
                lista.add(fila);
                if (c > 500) {
                    repository.saveAll(lista);
                    lista = new ArrayList<Archivo>();
                    c = 0;
                }
            }
            repository.saveAll(lista);//guarda el ultimo paquete de datos
            return 1;
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al leer el csv: " + e.getMessage());
        }
    }

}
