package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;

public class EncerramentoOrdemServico {
    @NotBlank (message = "Iforme a solução realizada")
    String solucao;

    public EncerramentoOrdemServico() {
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }
}
