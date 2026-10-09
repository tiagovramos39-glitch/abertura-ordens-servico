package br.com.ordensservico.aberturaordensservico.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    public final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> abrirOrdemServico(
            @Valid @RequestBody OrdemServicoRequest ordemServicoRequest) {
        Optional<OrdemServico> ordemServicoCadastradar = ordemServicoService.cadastrar(ordemServicoRequest);

        if (ordemServicoCadastradar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ordemServicoCadastradar.get());
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {
        List<OrdemServico> ordensServico = ordemServicoService.listar();
        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {
        Optional<OrdemServico> ordemServico = ordemServicoService.buscarPorId(id);
        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OrdemServico novosDados) {
        Optional<OrdemServico> ordemServicoAtualizada = ordemServicoService.atualizar(id, novosDados);
        if (ordemServicoAtualizada.isPresent()) {
            return ResponseEntity.ok(ordemServicoAtualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        boolean excluido = ordemServicoService.excluir(id);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}