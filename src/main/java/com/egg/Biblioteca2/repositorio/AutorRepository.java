/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca2.repositorio;

import com.egg.Biblioteca2.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 *
 * @author VANNI
 */
public interface AutorRepository extends JpaRepository<Autor, String> {
    
}
