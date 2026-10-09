package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service 
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest ordemServicoRequest) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(ordemServicoRequest.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServicoCadastrada = new OrdemServico();
        ordemServicoCadastrada.setDescricao(ordemServicoRequest.getDescricao());
        ordemServicoCadastrada.setEquipamento(equipamentoEncontrado.get());
        ordemServicoCadastrada.setDataAbertura(LocalDateTime.now());
        return Optional.of(ordemServicoRepository.save(ordemServicoCadastrada));
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> atualizar(Integer id, OrdemServico novosDados) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoRepository.findById(id);
        if (ordemServicoEncontrada.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = ordemServicoEncontrada.get();
        ordemServico.setDescricao(novosDados.getDescricao());
        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public boolean excluir(Integer id) {
        if(!ordemServicoRepository.existsById(id)) {
            return false;
        }
        ordemServicoRepository.deleteById(id);
        return true;
    }
}