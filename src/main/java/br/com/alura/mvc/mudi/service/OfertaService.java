package br.com.alura.mvc.mudi.service;

import br.com.alura.mvc.mudi.dto.NovaOfertaDTO;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.IOfertaRepository;
import br.com.alura.mvc.mudi.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfertaService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IOfertaRepository ofertaRepository;

    public Oferta salvar(NovaOfertaDTO novaOfertaDTO) {
        Optional<Pedido> pedido = pedidoRepository.findById(novaOfertaDTO.getPedidoId());
        if(!pedido.isPresent()) {
            return null;
        }
        Oferta oferta = novaOfertaDTO.toOferta();
        oferta.setPedido(pedido.get());
        oferta = ofertaRepository.save(oferta);

        pedido.get().getOfertas().add(oferta);
        pedidoRepository.save(pedido.get());

        return oferta;
    }
}
