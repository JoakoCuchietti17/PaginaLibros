/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akatsuki.itachi.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Personal
 */
@Entity
public class Autor {
    
    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String Id;
    private String nombre;

    public Autor() {
    }
    
    
    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
