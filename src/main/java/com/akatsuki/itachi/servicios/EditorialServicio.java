/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akatsuki.itachi.servicios;


import com.akatsuki.itachi.entidades.Editorial;
import com.akatsuki.itachi.excepciones.MiException;
import com.akatsuki.itachi.repositorio.EditorialRepositorio;
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
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialrepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialrepositorio.save(editorial);
    }
    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialrepositorio.findAll();
        return editoriales;
    }
    public void ModificarEditorial(String nombre, String id) throws MiException {
        validar(nombre);
        Optional<Editorial> respuesta = editorialrepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialrepositorio.save(editorial);
       }
    }
    private void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede estar vacio");
        }
    }
}
