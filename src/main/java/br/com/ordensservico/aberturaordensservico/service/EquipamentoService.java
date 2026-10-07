package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(Equipamento equipamento) {
        Optional<Setor> setorEncontrado = setorRepository.findById(equipamento.getSetor().getId());	

        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }
        Equipamento equipamentoCadastrado = new Equipamento();
        equipamentoCadastrado.setNome(equipamento.getNome());
        equipamentoCadastrado.setNumeroPatrimonio(equipamento.getNumeroPatrimonio());
        equipamentoCadastrado.setSetor(equipamento.getSetor());
        return Optional.of(equipamentoRepository.save(equipamentoCadastrado));
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer id, Equipamento novosDados) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);
        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();
        equipamento.setNome(novosDados.getNome());
        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public boolean excluir(Integer id) {
        if(!equipamentoRepository.existsById(id)) {
            return false;
        }
        equipamentoRepository.deleteById(id);
        return true;
    }
}