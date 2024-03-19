package br.com.fiap.budgetbuddy.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.budgetbuddy.model.Movimentacao;
import br.com.fiap.budgetbuddy.repository.MovimentacaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    @Autowired
    MovimentacaoRepository repository;

    @PostMapping
    @ResponseStatus(code = CREATED)
    public ResponseEntity<Movimentacao> create(@RequestBody @Valid Movimentacao movimentacao) {
        repository.save(movimentacao);
        return ResponseEntity.status(201).body(movimentacao);
    }



}
