package com.usco.springboot.app.service;

import java.util.List;

import com.usco.springboot.app.entity.Usuario;

public interface UsuarioService {
    List<Usuario>listar();
    Usuario listarId(Long id);
    Usuario add(Usuario p);
    Usuario edit(Usuario p);
    Usuario delete(Long id);
    
}