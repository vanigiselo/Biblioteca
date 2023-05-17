/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.servicios;

import com.egg.Biblioteca2.entidades.Editorial;
import com.egg.Biblioteca2.excepciones.MiException;
import com.egg.Biblioteca2.repositorio.EditorialRepository;
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
public class EditorialServicio {
    
     @Autowired
    private EditorialRepository editorialRepositorio;
    @Transactional
    public void crearEditorial(String nombre)throws MiException {
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }

    public void modificarEditorial(String nombre, String id) throws MiException{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }
    
        
    // @Transactional(readOnly = true)
    public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
    }
    
    private void validar(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre de la editorial no puede ser nulo o estar vacio");
        }
    }
}
