package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.Usuario;
import br.com.alura.mvc.mudi.model.enums.EStatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

    @Cacheable("pedidos")
    List<Pedido> findByStatus(EStatusPedido status, Pageable pageable);

    List<Pedido> findByUsuario(Usuario usuario);

    List<Pedido> findByStatusAndUsuario(EStatusPedido status, Usuario username);

    List<Pedido> findByStatusOrderByDataEntregaDesc(EStatusPedido status);
}
