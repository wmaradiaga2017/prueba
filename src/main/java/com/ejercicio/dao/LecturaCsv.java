/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.dao;

import com.ejercicio.repository.Archivo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author walter.maradiaga
 */
@Slf4j
public class LecturaCsv {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "Valor 1", "Valor 2", "Valor 3", "Valor 4", "Valor 5"};

    //validar si el archivo es csv
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Archivo> recorrerCSV(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            log.info("Aqui va el archivo");
            List<Archivo> lista = new ArrayList<Archivo>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord record : csvRecords) {
                Archivo fila = new Archivo(
                        Long.parseLong(record.get(0)),
                        record.get(1),
                        record.get(2),
                        record.get(3),
                        record.get(4),record.get(5));
                lista.add(fila);
            }
            return lista;
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al leer el csv: " + e.getMessage());
        }
    }

}
