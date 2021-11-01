/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bike.bike.servicios;

/**
 *
 * @author alberto
 */
import com.bike.bike.modelo.ContadorClientes;
import com.bike.bike.repositorio.RepositorioReservacion;
import com.bike.bike.modelo.Reservacion;
import com.bike.bike.modelo.StatusReservas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */


@Service
/**
 * Crea Clase Servicios reservacion donde se implementan las validaciones a los metodos CRUD 
 */
public class ServiciosReservacion {
    @Autowired
    private RepositorioReservacion metodosCrud;
    /**
    * Obtine todas las reservaciones
    * @return 
    */
    public List<Reservacion> getAll(){
        return metodosCrud.getAll();
    }
    /**
    * Obtiene reservación 
    * @param reservationId
    * @return 
    */
    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }
    /**
    * Guarda Reservación
    * @param reservation
    * @return 
    */
    public Reservacion save(Reservacion reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservacion> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    
    /**
     * Actuliza Reservación
     * @param reservation
     * @return 
     */

    public Reservacion update(Reservacion reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservacion> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    
    /**
     * Borrar Reservacion
     * @param reservationId
     * @return 
     */

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    /**
     * Generrar reporte
     * @return 
     */
    public StatusReservas reporteStatusServicio (){
            List<Reservacion>completed= metodosCrud.ReservacionStatusRepositorio("completed");
            List<Reservacion>cancelled= metodosCrud.ReservacionStatusRepositorio("cancelled");
        
         return new StatusReservas(completed.size(), cancelled.size() );
    }
    
    /**
     * Reporte tiempo
     * @param datoA
     * @param datoB
     * @return 
     */
    public List<Reservacion> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    }
    
    /** 
     * Reportes clientes servicios
     * @return 
     */
    public List<ContadorClientes> reporteClientesServicio(){
            return metodosCrud.getClientesRepositorio();
    }  
}





