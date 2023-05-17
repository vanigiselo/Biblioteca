/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.controladores;

import com.egg.Biblioteca2.entidades.Autor;
import com.egg.Biblioteca2.excepciones.MiException;
import com.egg.Biblioteca2.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
/**
 *
 * @author VANNI
 */
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    
    @GetMapping("/registrar") //localhost8080/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        System.out.println("nombre: " + nombre);
        try {
     
            autorServicio.crearAutor(nombre);
           
        } catch (MiException ex) {
            java.util.logging.Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        return "index.html";
    }


    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores", autores);
        
        return "autor_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar (@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorServicio.getOne(id));
        return "autor_modificar.html";
    }
    
    @PostMapping ("/modificar/{id}")
    public String modificar (@PathVariable String id, String nombre, ModelMap modelo){
       
        try {
            autorServicio.modificarAutor(nombre, id);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }
       
        
    }
}
