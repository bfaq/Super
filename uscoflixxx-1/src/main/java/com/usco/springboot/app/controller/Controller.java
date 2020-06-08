package com.usco.springboot.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.springboot.app.dto.JwtDTO;
import com.usco.springboot.app.dto.LoginUsuario;
import com.usco.springboot.app.dto.Mensaje;
import com.usco.springboot.app.dto.NuevoUsuario;
import com.usco.springboot.app.entity.Rol;
import com.usco.springboot.app.entity.Usuario;
import com.usco.springboot.app.enums.RolNombre;
import com.usco.springboot.app.security.JWT.JwtProvider;
import com.usco.springboot.app.service.RolService;
import com.usco.springboot.app.service.UsuarioServiceImp;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/usuarios")
public class Controller {
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioServiceImp service;
    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    @GetMapping
    public List<Usuario>listar(){
        return service.listar();
    }
   
    @PostMapping("/nuevo")
    
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        if(service.existePorNombre(nuevoUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("ese username ya existe"), HttpStatus.BAD_REQUEST);
        if(service.existePorEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getUsername(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<String> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
        for (String rol : rolesStr) {
            switch (rol) {
                case "admin":
                    Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
                    roles.add(rolAdmin);
                    break;
                default:
                    Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
                    roles.add(rolUser);
            }
        }
        usuario.setRoles(roles);
        service.add(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }
    /*
    @PostMapping()
    public ResponseEntity<?> agregar(@RequestBody Usuario p){
    	if(StringUtils.isEmpty(p.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isEmpty(p.getUsername()))
            return new ResponseEntity(new Mensaje("Los apellidos son obligatorios"), HttpStatus.BAD_REQUEST);
    	
    	if(StringUtils.isEmpty(p.getEmail()))
             return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
    	 if(service.existePorEmail(p.getEmail()))
             return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
    	 if(StringUtils.isEmpty(p.getPassword()))
             return new ResponseEntity(new Mensaje("Password es obligatorio"), HttpStatus.BAD_REQUEST);
         service.add(p);
         return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }
    */
    //@PostMapping
    //public Usuario agregar(@RequestBody Usuario p){
    //    return service.add(p);
    //}
    /*
    @GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Usuario validateLogin() {
		return new Usuario("El usuario ha sido autentificado");
	}
	*/
    //@GetMapping(produces = "application/json")
   	//@RequestMapping({ "/validateLogin" })
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
   
    @GetMapping(path = {"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario listarId(@PathVariable("id")Long id){
        return service.listarId(id);
    }
    @PutMapping(path = {"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> edit(@RequestBody Usuario p, @PathVariable("id") Long id){
    	if(service.existePorEmail(p.getEmail()) && service.obtenerPorEmail(p.getEmail()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
    	if(service.existePorNombre(p.getUsername()) && service.getByUsername(p.getUsername()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese username ya existe"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isEmpty(p.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isEmpty(p.getUsername()))
            return new ResponseEntity(new Mensaje("el username es obligatorio"), HttpStatus.BAD_REQUEST);
    	
    	if(StringUtils.isEmpty(p.getEmail()))
             return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
    	 if(StringUtils.isEmpty(p.getPassword()))
             return new ResponseEntity(new Mensaje("Password es obligatorio"), HttpStatus.BAD_REQUEST);
    	p.setId(id);
        service.edit(p);	
        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.CREATED);
    }
    /*
    @PutMapping(path = {"/{id}"})
    public Usuario editar(@RequestBody Usuario p,@PathVariable("id") int id){
        p.setId(id);
        return service.edit(p);
    }
    */
    @DeleteMapping(path = {"/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
}