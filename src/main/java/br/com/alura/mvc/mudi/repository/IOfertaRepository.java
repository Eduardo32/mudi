package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfertaRepository extends JpaRepository<Oferta, Long> {
}
