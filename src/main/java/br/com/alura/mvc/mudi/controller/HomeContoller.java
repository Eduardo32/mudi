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
@RequestMapping("/home")
public class HomeContoller {

    private static final String HOME = "home";

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String home(Model model){
        List<Pedido> pedidos = pedidoService.buscarPorStatus(EStatusPedido.ENTREGUE);
        model.addAttribute("pedidos", pedidos);
        return HOME;
    }
}
