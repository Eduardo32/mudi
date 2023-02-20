package br.com.alura.mvc.mudi.service;

import br.com.alura.mvc.mudi.dto.NovoPedidoDTO;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.Usuario;
import br.com.alura.mvc.mudi.model.enums.EStatusPedido;
import br.com.alura.mvc.mudi.repository.IPedidoRepository;
import br.com.alura.mvc.mudi.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public void salvar(NovoPedidoDTO novoPedidoDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(novoPedidoDTO.getUsername());
        Pedido pedido = novoPedidoDTO.toPedido();
        pedido.setUsuario(usuario.get());
        pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPorStatus(EStatusPedido status) {
        Sort sort = Sort.by("dataEntrega").descending();
        PageRequest pageRequest = PageRequest.of(0, 7, sort);

        return pedidoRepository.findByStatus(status, pageRequest);
    }

    public List<Pedido> buscarTodosPorUsuario(String username) {
        Usuario usuario = usuarioRepository.findById(username).get();
        return pedidoRepository.findByUsuario(usuario);
    }

    public List<Pedido> buscarPedidoUsuarioPorStatus(EStatusPedido status, String username) {
        Usuario usuario = usuarioRepository.findById(username).get();
        return pedidoRepository.findByStatusAndUsuario(status, usuario);
    }
}
