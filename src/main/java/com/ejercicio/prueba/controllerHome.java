/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.prueba;

import com.ejercicio.dao.DataServicio;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author walter.maradiaga
 */

@Controller
@Slf4j
public class controllerHome {

    @GetMapping("/")
    public String inicio(Model model) throws Exception{     
        DataServicio informacion = new DataServicio();   
        //log.info("ejecutando el controlador Spring MVC");
        List<String> paises = informacion.paises();
        model.addAttribute("paises", paises);
        return "index";
    }
    
}
