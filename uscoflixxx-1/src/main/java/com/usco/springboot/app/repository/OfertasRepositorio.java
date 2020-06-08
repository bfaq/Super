package com.usco.springboot.app.repository;
import com.usco.springboot.app.entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface OfertasRepositorio extends JpaRepository<Oferta,Long> {
	 Optional<Oferta>findByNombreProducto(String no);
	    boolean existsByNombreProducto(String no);
}
