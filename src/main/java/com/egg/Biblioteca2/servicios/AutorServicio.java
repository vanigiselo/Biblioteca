/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.servicios;

import com.egg.Biblioteca2.entidades.Autor;
import com.egg.Biblioteca2.excepciones.MiException;
import com.egg.Biblioteca2.repositorio.AutorRepository;
import java.util.ArrayList;
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
public class AutorServicio {
    
      @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validarAutor(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);

        autorRepository.save(autor);
    }

    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList();

        autores = autorRepository.findAll();

        return autores;
    }

    public void modificarAutor(String nombre, String id) throws MiException{
        Optional<Autor> respuesta = autorRepository.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepository.save(autor);
        }
    }
    
    public Autor getOne(String id){
        return autorRepository.getOne(id);
    }
    
    private void validarAutor(String nombre) throws MiException{
            
        if (nombre.isEmpty() || nombre == null){
            throw new MiException("El nombre no puede ser nulo");
    }
    }
    
     // @Transactional(readOnly = true)
    //public Autor getOne(String id){
       // return autorRepository.getOne(id);
   // }
    
   
    @Transactional
    public void eliminar(String id) throws MiException{
        
        Autor autor = autorRepository.getById(id);
        
        autorRepository.delete(autor);

    }
    
     private void validar(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
    }
}
