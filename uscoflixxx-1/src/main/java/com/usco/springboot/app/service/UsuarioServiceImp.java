package com.usco.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.springboot.app.entity.Usuario;
import com.usco.springboot.app.repository.UsuarioRepositorio;

@Service
public class UsuarioServiceImp implements UsuarioService{
    @Autowired
    private UsuarioRepositorio repositorio;
    
    @Override
    public List<Usuario> listar() {
        return repositorio.findAll();
    }

    @Override
    public Usuario listarId(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Usuario add(Usuario p) {
        return repositorio.save(p);
    }

    @Override
    public Usuario edit(Usuario p) {
        return repositorio.save(p);
    }

    @Override
    public Usuario delete(Long id) {
    	Usuario p=repositorio.findById(id);
        if(p!=null){
            repositorio.delete(p);
        }
       return p;
    }
    public Optional<Usuario> obtenerPorEmail(String email){
        return repositorio.findByEmail(email);
    }
    public boolean existePorEmail(String email){
        return repositorio.existsByEmail(email);
    }
    public Optional<Usuario> getByUsername(String nu){
        return repositorio.findByUsername(nu);
    }

    public boolean existePorNombre(String nu){
        return repositorio.existsByUsername(nu);
    }
    
}