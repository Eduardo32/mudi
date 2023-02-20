package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.dto.NovaOfertaDTO;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRS {

    @Autowired
    private OfertaService ofertaService;

    @PostMapping
    public Oferta criar(@Valid @RequestBody NovaOfertaDTO novaOfertaDTO) {
        return ofertaService.salvar(novaOfertaDTO);
    }
}
