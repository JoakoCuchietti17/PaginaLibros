/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akatsuki.itachi.servicios;

import com.akatsuki.itachi.entidades.Autor;
import com.akatsuki.itachi.entidades.Editorial;
import com.akatsuki.itachi.entidades.Libro;
import com.akatsuki.itachi.excepciones.MiException;
import com.akatsuki.itachi.repositorio.AutorRepositorio;
import com.akatsuki.itachi.repositorio.EditorialRepositorio;
import com.akatsuki.itachi.repositorio.Librorepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class LibroServicio {
    
    @Autowired
    private Librorepositorio libroRepositorio;
      @Autowired
    private AutorRepositorio autorrepositorio;
       @Autowired
    private EditorialRepositorio editorialrepositorio;
    
         @Transactional
    public void crearLibro(Long isbn , String titulo,Integer Ejemplares,String idAutor,String idEditorial) throws MiException{
        
        validar(isbn, titulo, Ejemplares, idAutor, idEditorial);
        Autor autor = autorrepositorio.findById(idAutor).get();
        Libro libro= new Libro();
        Editorial editorial =  editorialrepositorio.findById(idEditorial).get();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(Ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
        
    }
    
    public List<Libro> listarLibros(){
        
        List<Libro> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
        
        return libros;
    }
    
    
    public void modificarLibro(Long isbn, String titulo , String idAutor , String idEditorial, Integer ejemplares) throws MiException{
        
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
      Optional<Autor> respuestaAutor = autorrepositorio.findById(idAutor);
       Optional<Editorial> respuestaEditorial = editorialrepositorio.findById(idEditorial);
       
       Autor autor = new Autor();
       Editorial editorial = new Editorial();
       
       if(respuestaAutor.isPresent()){
           autor = respuestaAutor.get();
       }
       if(respuestaEditorial.isPresent()){
           editorial = respuestaEditorial.get();
       }
        if(respuesta.isPresent()){
            
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
        }
    }
    
    private void validar(Long isbn , String titulo , Integer Ejemplares,String idAutor , String idEditorial) throws MiException{
        
         if(isbn==null){
            throw new MiException("el isbn no puede ser nulo");
        }
        
        if(titulo.isEmpty() ||  titulo==null){
             throw new MiException("el titulo no puede ser nulo");
        }
        if(Ejemplares==null){
             throw new MiException("el ejemplares no puede ser nulo");
        }
        if(idAutor.isEmpty() ||  idAutor==null){
             throw new MiException("el id del autor no puede ser nulo");
        }
        if(idEditorial.isEmpty() ||  idEditorial==null){
             throw new MiException("el id del editorial no puede ser nulo");
        }
    }
}
