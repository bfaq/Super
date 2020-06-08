package com.usco.springboot.app.controller;
import com.usco.springboot.app.dto.Mensaje;
import com.usco.springboot.app.entity.Oferta;
import com.usco.springboot.app.entity.SuperMercado;
import com.usco.springboot.app.repository.SuperRespositorio;
import com.usco.springboot.app.service.OfertasService;
import com.usco.springboot.app.service.SuperService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ofertas")
@CrossOrigin(origins = "http://localhost:4200")
public class OfertasController {

    @Autowired
    OfertasService ofertaService;
    
    @Autowired
    SuperService superService;
    
    @GetMapping("/listaRep")
    public ResponseEntity<List<SuperMercado>>getLista1(){
    	List<SuperMercado> listaa = superService.obtenerTodos();
		return new ResponseEntity<List<SuperMercado>>(listaa,HttpStatus.OK);
    	
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Oferta>> getLista(){
        List<Oferta> lista = ofertaService.obtenerTodos();
        return new ResponseEntity<List<Oferta>>(lista, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Oferta> getOne(@PathVariable Long id){
        if(!ofertaService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        Oferta oferta = ofertaService.obtenerPorId(id).get();
        return new ResponseEntity<Oferta>(oferta, HttpStatus.OK);
    }

    @PostMapping("nuevo")
    public ResponseEntity<?> create(@RequestBody Oferta oferta){
        if(StringUtils.isBlank(oferta.getNombreProducto()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(oferta.getTipo()))
            return new ResponseEntity(new Mensaje("el tipo de oferta es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(oferta.getImagen()))
            return new ResponseEntity(new Mensaje("olvidaste insertar la img uwu"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(oferta.getDescripcion()))
            return new ResponseEntity(new Mensaje("falta la descripción uwu"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(oferta.getVigencia()))
            return new ResponseEntity(new Mensaje("falta la  vigencia uwu"), HttpStatus.BAD_REQUEST);
        if((Integer)oferta.getPrecio() == null || oferta.getPrecio()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio"), HttpStatus.BAD_REQUEST);
       
        if(ofertaService.existePorNombre(oferta.getNombreProducto()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        ofertaService.guardar(oferta);
        return new ResponseEntity(new Mensaje("producto guardado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody Oferta oferta, @PathVariable("id") Long id){
        if(!ofertaService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(oferta.getNombreProducto()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if((Integer)oferta.getPrecio() == null || oferta.getPrecio()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio"), HttpStatus.BAD_REQUEST);
        if(ofertaService.existePorNombre(oferta.getNombreProducto()) &&
        		ofertaService.obtenerPorNombre(oferta.getNombreProducto()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Oferta prodUpdate = ofertaService.obtenerPorId(id).get();
        prodUpdate.setImagen(oferta.getImagen());
        prodUpdate.setNombreProducto(oferta.getNombreProducto());
        prodUpdate.setDescripcion(oferta.getDescripcion());
        prodUpdate.setTipo(oferta.getTipo());
        prodUpdate.setVigencia(oferta.getVigencia());
        prodUpdate.setSupermercado(oferta.getSupermercado());
        prodUpdate.setPrecio(oferta.getPrecio());
        ofertaService.guardar(prodUpdate);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!ofertaService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        ofertaService.borrar(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}