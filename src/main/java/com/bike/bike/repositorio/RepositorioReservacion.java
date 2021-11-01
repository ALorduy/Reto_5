/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bike.bike.repositorio;

import com.bike.bike.interfac.InterfaceReservacion;
import com.bike.bike.modelo.Cliente;
import com.bike.bike.modelo.ContadorClientes;
import com.bike.bike.modelo.Reservacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alberto
 */
@Repository
public class RepositorioReservacion {
       @Autowired
    private InterfaceReservacion crud;

    public List<Reservacion> getAll(){
        return (List<Reservacion>) crud.findAll();
    }
    public Optional<Reservacion> getReservation(int id){
        return crud.findById(id);
    }
    public Reservacion save(Reservacion reservation){
        return crud.save(reservation);
    }
    public void delete(Reservacion reservation){
        crud.delete(reservation);
    }
    
    public List<Reservacion> ReservacionStatusRepositorio (String status){
         return crud.findAllByStatus(status);
     }
     
     public List<Reservacion> ReservacionTiempoRepositorio (Date a, Date b){
         return crud.findAllByStartDateAfterAndStartDateBefore(a, b);
     
     }
     
     public List<ContadorClientes> getClientesRepositorio(){
         List<ContadorClientes> res = new ArrayList<>();
         List<Object[]> report = crud.countTotalReservacionByCliente();
         for(int i=0; i<report.size(); i++){
             res.add(new ContadorClientes((Long)report.get(i)[1],(Cliente) report.get(i)[0]));
         }
         return res;
     }
    
}
