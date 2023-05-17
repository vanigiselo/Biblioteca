/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.controladores;

import com.egg.Biblioteca2.excepciones.MiException;
import com.egg.Biblioteca2.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")

/**
 *
 * @author VANNI
 */
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/")
    public String index(){
        
        return "index.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo){
     
        try {
            usuarioServicio.registrar(nombre, email, password, password2);
            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (MiException ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            
             modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }
        
    }
    
    @GetMapping("/login")
    public String login(@RequestParam (required = false) String error, ModelMap modelo){
        if (error != null) {
            modelo.put("error", "Usuario o contraseña incorrecta");
            
        }
        return "login.html";
    }
     @GetMapping("/inicio")
    public String inicio(){
      
        return "inicio.html";
    }
    
}
