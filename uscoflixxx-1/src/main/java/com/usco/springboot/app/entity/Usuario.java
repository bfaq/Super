package com.usco.springboot.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


	@Entity
	@Table(name = "usuarios")
	public class Usuario {
	    
	    @Id
	    @Column
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @NotEmpty
	    @Column
	    private String nombre;
	    @NotEmpty
	    @Column(unique = true)
	    private String username;
	    @Email
	    @NotEmpty
	    @Column(unique = true) 
	    private String email;
	    @NotEmpty
	    @Column 
	    private String password;
	    @Column
	    private String status;
	    @NotNull
	    @ManyToMany
	    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	    private Set<Rol> roles = new HashSet<>();
	    
	    public Usuario() {}
	    public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String email, @NotNull String password) {
	        this.nombre = nombre;
	        this.username = username;
	        this.email = email;
	        this.password = password;
	    }
		public Usuario(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
			
		}
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String  username) {
			this. username =  username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		 public Set<Rol> getRoles() {
		        return roles;
		    }

		    public void setRoles(Set<Rol> roles) {
		        this.roles = roles;
		    }
}

	

	

