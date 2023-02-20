package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Oferta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NovaOfertaDTO {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @NotNull
    private Long pedidoId;

    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
    private String valor;

    @NotNull
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    private String dataEntrega;
    private String comentario;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Oferta toOferta() {
        return new Oferta(new BigDecimal(this.getValor()), LocalDate.parse(this.getDataEntrega(), FORMATTER), this.comentario);
    }
}
