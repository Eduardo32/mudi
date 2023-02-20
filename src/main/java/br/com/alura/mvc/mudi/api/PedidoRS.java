package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.enums.EStatusPedido;
import br.com.alura.mvc.mudi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRS {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getPedidos(@Param("status") String status) {
        return pedidoService.buscarPorStatus(EStatusPedido.valueOf(status.toUpperCase()));
    }
}
