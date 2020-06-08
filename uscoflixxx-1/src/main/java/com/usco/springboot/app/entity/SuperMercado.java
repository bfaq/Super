package com.usco.springboot.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "supermercado")
public class SuperMercado {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @NotBlank
     @Column
     private String nombreSuper;
	 @Column
     private String img;
	 @Column
     private String direccion;
	 @Column
     private String telefono;
	 
	 public SuperMercado() {
		 
	 }
	public Long getId() {
		return id;
	}
	
	public SuperMercado(Long id, @NotBlank String nombreSuper, String img, String direccion, String telefono) {
		super();
		this.id = id;
		this.nombreSuper = nombreSuper;
		this.img = img;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreSuper() {
		return nombreSuper;
	}
	public void setNombreSuper(String nombreSuper) {
		this.nombreSuper = nombreSuper;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperMercado other = (SuperMercado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuperMercado [id=" + id + ", nombreSuper=" + nombreSuper + ", img=" + img + ", direccion=" + direccion
				+ ", telefono=" + telefono + "]";
	}
	 
	 
}
