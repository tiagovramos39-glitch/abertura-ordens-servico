package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
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
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.service.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrar(
            @RequestBody Setor setor) {
        Setor setorCadastrado = setorService.cadastrar(setor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(setorCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Setor>> listar() {
        List<Setor> setores = setorService.listar();
        return ResponseEntity.ok(setores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(@PathVariable Integer id) {
        Optional<Setor> setor = setorService.buscarPorId(id);
        if (setor.isPresent()) {
            return ResponseEntity.ok(setor.get());
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(
            @PathVariable Integer id,
            @RequestBody Setor novosDados) {
        Optional<Setor> setorAtualizado = setorService.atualizar(id, novosDados);
        if (setorAtualizado.isPresent()) {
            return ResponseEntity.ok(setorAtualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir
    (@PathVariable Integer id) {
        boolean excluido = setorService.excluir(id);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}