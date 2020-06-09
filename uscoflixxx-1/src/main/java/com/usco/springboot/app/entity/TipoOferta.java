package com.usco.springboot.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tipo_oferta")
public class TipoOferta {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id_tipo;
	 @NotBlank
     @Column
     private String descripcion;
	
	 public TipoOferta() {
		 
	 }
	public Long getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(Long id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TipoOferta(Long id_tipo, @NotBlank String descripcion) {
		super();
		this.id_tipo = id_tipo;
		this.descripcion = descripcion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_tipo == null) ? 0 : id_tipo.hashCode());
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
		TipoOferta other = (TipoOferta) obj;
		if (id_tipo == null) {
			if (other.id_tipo != null)
				return false;
		} else if (!id_tipo.equals(other.id_tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TipoOferta [id_tipo=" + id_tipo + ", descripcion=" + descripcion + "]";
	}

	 
}
