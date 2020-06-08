package com.usco.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.springboot.app.entity.SuperMercado;

import com.usco.springboot.app.repository.SuperRespositorio;

@Service
@Transactional
public class SuperService {
	 @Autowired
	 SuperRespositorio SuperRep;

	    public List<SuperMercado> obtenerTodos(){
	        List<SuperMercado> lista = SuperRep.findAll();
	        return lista;
	    }
}
