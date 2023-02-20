package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.enums.EStatusPedido;
import br.com.alura.mvc.mudi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final String USUARIO_HOME = "usuario/home";

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedido")
    public String home(Model model, Principal principal){
        List<Pedido> pedidos = pedidoService.buscarTodosPorUsuario(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return USUARIO_HOME;
    }

    @GetMapping("/pedido/{status}")
    public String buscarPorStatus(@PathVariable("status") String status, Model model, Principal principal){
        List<Pedido> pedidos = pedidoService.buscarPedidoUsuarioPorStatus(EStatusPedido.valueOf(status.toUpperCase()),
                principal.getName());
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return USUARIO_HOME;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/"+USUARIO_HOME;
    }
}
