/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akatsuki.itachi.servicios;

import com.akatsuki.itachi.entidades.Autor;
import com.akatsuki.itachi.excepciones.MiException;
import com.akatsuki.itachi.repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Personal
 */
@Service
public class AutorServicio {
    
    @Autowired
   private AutorRepositorio autorrepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setId(nombre);
       
        
        
        autorrepositorio.save(autor);
    }
    
     public List<Autor> listarAutores(){
        
        List<Autor> autores = new ArrayList();
        
        autores = autorrepositorio.findAll();
        
        return autores;
    }
     
     public void ModificarAutor(String nombre , String id) throws MiException{
         validar(nombre);
         Optional<Autor> respuesta = autorrepositorio.findById(id);
         
          
         if(respuesta.isPresent()){
             Autor autor = respuesta.get();
             
             autor.setNombre(nombre);
             autorrepositorio.save(autor);
            
         }
     }
     
     public Autor getOne(String id){
         return autorrepositorio.getOne(id);
     }
     
    
     
     private void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede estar vacio");
        }
        
    }
    
}
