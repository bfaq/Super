package com.usco.springboot.app.entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "oferta")
public class Oferta {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @NotBlank
     @Column
     private String nombreProducto;
	 @Column
     private String tipo;
	 @Column
     private String imagen;
	 @Column
     private String descripcion;
	 @Column
     private String vigencia;
	 @ManyToOne(cascade=CascadeType.MERGE)
	 @JoinColumn(name="id_super", referencedColumnName="id")
     private SuperMercado supermercado;
	 @NotNull
	 private int precio;
	 
	 public Oferta() {
	  
	 }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public SuperMercado getSupermercado() {
		return supermercado;
	}
	public void setSupermercado(SuperMercado supermercado) {
		this.supermercado = supermercado;
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
		Oferta other = (Oferta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Oferta(Long id, @NotBlank String nombreProducto, String tipo, String imagen, String descripcion,
			String vigencia, SuperMercado supermercado, @NotNull int precio) {
		super();
		this.id = id;
		this.nombreProducto = nombreProducto;
		this.tipo = tipo;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.vigencia = vigencia;
		this.supermercado = supermercado;
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Oferta [id=" + id + ", nombreProducto=" + nombreProducto + ", tipo=" + tipo + ", imagen=" + imagen
				+ ", descripcion=" + descripcion + ", vigencia=" + vigencia + ", supermercado=" + supermercado
				+ ", precio=" + precio + "]";
	}
	



}
