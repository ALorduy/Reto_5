/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bike.bike.interfac;

import com.bike.bike.modelo.Mensaje;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface InterfaceMensaje extends CrudRepository<Mensaje,Integer> {
    
}
