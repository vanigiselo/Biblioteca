/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.controladores;

import com.egg.Biblioteca2.entidades.Autor;
import com.egg.Biblioteca2.entidades.Editorial;
import com.egg.Biblioteca2.entidades.Libro;
import com.egg.Biblioteca2.excepciones.MiException;
import com.egg.Biblioteca2.servicios.AutorServicio;
import com.egg.Biblioteca2.servicios.EditorialServicio;
import com.egg.Biblioteca2.servicios.LibroServicio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
/**
 *
 * @author VANNI
 */
public class LibroControlador {
    
     @Autowired
     private LibroServicio libroServicio;
      
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/registrar") //localhost8080/autor/registrar
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        List <Editorial> editoriales =  editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        
        return "libro_form.html";
        
    }
    
    @PostMapping("/registro")
    public String registro (@RequestParam(required= false) Long isbn, @RequestParam String titulo, @RequestParam(required= false) Integer ejemplares, @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo){
        
         try {
             libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
             modelo.put("exito", "El libro fue cargado correctamente");
         } catch (MiException ex) {
             List<Autor> autores = autorServicio.listarAutores();
             List <Editorial> editoriales =  editorialServicio.listarEditoriales();
        
             modelo.addAttribute("autores", autores);
             modelo.addAttribute("editoriales", editoriales);
             modelo.put("error", ex.getMessage());
            // Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
              return "libro_form.html";
         }
        
         return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
         List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libros", libros);

        return "libro_list";  
    
    }
    
     @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {
      
        modelo.put("libro", libroServicio.getOne(isbn));
        
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        
        return "libro_modificar.html";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap modelo) {
        try {
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();
            
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            libroServicio.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);
            
                        
            return "redirect:../lista";

        } catch (MiException ex) {
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();
            
            modelo.put("error", ex.getMessage());
            
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            
            return "libro_modificar.html";
        }

    }
        
}
