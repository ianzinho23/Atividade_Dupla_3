package com.example.aula.controller;

import com.example.aula.model.Prato;
import com.example.aula.service.PratoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @GetMapping
    public ResponseEntity<List<Prato>> listarTodos() {
        return ResponseEntity.ok(pratoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> salvar(@Valid @RequestBody Prato prato) {
        pratoService.salvar(prato);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Prato cadastrado com sucesso."));
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> atualizar(@Valid @RequestBody Prato prato) {
        pratoService.atualizar(prato);
        return ResponseEntity
                .ok(Map.of("mensagem", "Prato atualizado com sucesso."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluir(@PathVariable Long id) {
        pratoService.excluir(id);
        return ResponseEntity
                .ok(Map.of("mensagem", "Prato excluído com sucesso."));
    }
    
}
