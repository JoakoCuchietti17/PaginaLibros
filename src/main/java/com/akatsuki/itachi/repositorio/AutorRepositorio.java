/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akatsuki.itachi.repositorio;

import com.akatsuki.itachi.entidades.Autor;
import com.akatsuki.itachi.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Personal
 */
@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
}
    

