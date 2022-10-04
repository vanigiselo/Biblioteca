/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.repositorio;

import com.egg.Biblioteca2.entidades.Libro;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
/**
 *
 * @author VANNI
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
   @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo") 
   public Libro BuscarPorTitulo(@Param("titulo") String titulo);
   
   @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
   public List<Libro> BuscarPorAutor (@Param("nombre")String nombre);
    
}