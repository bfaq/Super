package com.usco.springboot.app.service;
import com.usco.springboot.app.entity.Oferta;
import com.usco.springboot.app.repository.OfertasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OfertasService {

    @Autowired
    OfertasRepositorio ofertaRepository;

    public List<Oferta> obtenerTodos(){
        List<Oferta> lista = ofertaRepository.findAll();
        return lista;
    }

    public Optional<Oferta> obtenerPorId(Long id){
        return ofertaRepository.findById(id);
    }

    public Optional<Oferta> obtenerPorNombre(String np){
        return ofertaRepository.findByNombreProducto(np);
    }

    public void guardar(Oferta producto){
    	ofertaRepository.save(producto);
    }

    public void borrar(Long id){
    	ofertaRepository.deleteById(id);
    }

    public boolean existePorNombre(String np){
        return ofertaRepository.existsByNombreProducto(np);
    }

    public boolean existePorId(Long id){
        return ofertaRepository.existsById(id);
    }
}
