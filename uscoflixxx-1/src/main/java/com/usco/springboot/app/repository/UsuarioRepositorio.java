package com.usco.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import com.usco.springboot.app.entity.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    public List<Usuario>findAll();
    public Usuario findById(Long id);
    public Usuario save(Usuario p);
    public void delete(Usuario p);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    
    Optional<Usuario> findByUsername(String nu);
    boolean existsByUsername(String nu);
   

}