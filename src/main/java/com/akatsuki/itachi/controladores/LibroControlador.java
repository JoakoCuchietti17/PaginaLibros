package com.akatsuki.itachi.controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.akatsuki.itachi.entidades.Autor;
import com.akatsuki.itachi.entidades.Editorial;
import com.akatsuki.itachi.entidades.Libro;
import com.akatsuki.itachi.excepciones.MiException;
import com.akatsuki.itachi.servicios.AutorServicio;
import com.akatsuki.itachi.servicios.EditorialServicio;
import com.akatsuki.itachi.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores" , autores);
        modelo.addAttribute("editoriales" , editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) Long isbn , @RequestParam String titulo,@RequestParam(required=false) Integer ejemplares , @RequestParam String idAutor, @RequestParam String idEditorial , ModelMap modelo){
        try{
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            modelo.put("exito", "El libro fue cargado correctamente");
        }catch (MiException ex){

            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
      @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libros" , libros);
        
        return "libro_list.html";



}
}