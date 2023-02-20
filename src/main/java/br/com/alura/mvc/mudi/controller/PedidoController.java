package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.NovoPedidoDTO;
import br.com.alura.mvc.mudi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    private static final String PEDIDO_FORMULARIO = "pedido/formulario";
    private static final String HOME = "home";
    private static final String USUARIO_PEDIDO = "usuario/pedido";

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/formulario")
    public String formulario(NovoPedidoDTO novoPedidoDTO) {
        return PEDIDO_FORMULARIO;
    }

    @PostMapping("/novo")
    public String novo(@Valid NovoPedidoDTO novoPedidoDTO, BindingResult result) {
        if(result.hasErrors()) {
            return PEDIDO_FORMULARIO;
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        novoPedidoDTO.setUsername(username);

        pedidoService.salvar(novoPedidoDTO);
        return "redirect:/" + USUARIO_PEDIDO;
    }
}
