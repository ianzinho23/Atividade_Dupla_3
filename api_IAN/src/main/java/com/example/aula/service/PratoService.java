package com.example.aula.service;

import com.example.aula.model.Prato;
import com.example.aula.repository.PratoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PratoService {

    private final PratoRepository pratoRepository;

    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public List<Prato> listarTodos() {
        return pratoRepository.findAll();
    }

    public Prato salvar(@Valid Prato prato) {
        return pratoRepository.save(prato);
    }

    public Prato atualizar(@Valid Prato prato) {
        Prato pratoAtualizar = pratoRepository.findById(prato.getId())
                .orElseThrow(() -> new IllegalArgumentException("Prato não encontrado."));

        pratoAtualizar.setNomePrato(prato.getNomePrato());
        pratoAtualizar.setDescricao(prato.getDescricao());
        pratoAtualizar.setPreco(prato.getPreco());
        pratoAtualizar.setCategoria(prato.getCategoria());
        pratoAtualizar.setDisponibilidade(prato.getDisponibilidade());
        pratoAtualizar.setUrlImagem(prato.getUrlImagem());

        return pratoRepository.save(pratoAtualizar);
    }

    public void excluir(Long id) {
        Prato pratoExcluir = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));

        pratoRepository.deleteById(pratoExcluir.getId());
    }
}
