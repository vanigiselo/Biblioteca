/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.controladores;

import com.egg.Biblioteca2.servicios.AutorServicio;
import com.egg.Biblioteca2.servicios.EditorialServicio;
import com.egg.Biblioteca2.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editorial")
/**
 *
 * @author VANNI
 */
public class EditorialControlador {
      @Autowired
     private LibroServicio libroServicio;
      
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;

}
