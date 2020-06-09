package com.usco.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.springboot.app.entity.TipoOferta;
import com.usco.springboot.app.repository.TipoOfertasRepositorio;

@Service
@Transactional
public class TipoOfertaService {
	@Autowired
	TipoOfertasRepositorio tipoofertarep;
	public List<TipoOferta> obtenerTodos(){
        List<TipoOferta> lista = tipoofertarep.findAll();
        return lista;
    }
}
