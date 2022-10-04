/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.servicios;

import com.egg.Biblioteca2.entidades.Autor;
import com.egg.Biblioteca2.entidades.Editorial;
import com.egg.Biblioteca2.entidades.Libro;
import com.egg.Biblioteca2.excepciones.MiException;
import com.egg.Biblioteca2.repositorio.AutorRepository;
import com.egg.Biblioteca2.repositorio.EditorialRepository;
import com.egg.Biblioteca2.repositorio.LibroRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
/**
 *
 * @author VANNI
 */
public class LibroServicio {
    
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial)throws MiException{
        
        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepository.save(libro);
    }
    
    public List<Libro> listarLibros(){
        List<Libro> libros = new ArrayList();
        
        libros = libroRepository.findAll();
        
        return libros;
    }
    
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{
        
        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        Optional<Libro> respuesta = libroRepository.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepository.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepository.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if(respuestaAutor.isPresent()){
            autor = respuestaAutor.get();
        }
        
        if(respuestaEditorial.isPresent()){
            editorial = respuestaEditorial.get();
        }
        
        if(respuesta.isPresent()){
            Libro libro= respuesta.get();
            
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            libroRepository.save(libro);
        }
    }
    
    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares)throws MiException{
        
        if(isbn == null){
            throw new MiException("El isbn no puede ser nulo");
        }
        
        if(titulo.isEmpty() || titulo == null){
            throw new MiException("El titulo no puede ser nulo o estar vacío");
        }
        
        if(ejemplares == null){
            throw new MiException("El número de ejemplares no puede ser nulo");
        }
        
        if(idAutor.isEmpty() || idAutor == null){
            throw new MiException("idAutor no puede ser nulo o estar vacío");
        }
        
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiException("idEditorial no puede ser nulo o estar vacío");
        }
    }
    // @Transactional(readOnly = true)
    public Libro getOne(Long isbn){
       return libroRepository.getOne(isbn);
    }
    
}
