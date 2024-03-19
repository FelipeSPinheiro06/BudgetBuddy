package br.com.fiap.budgetbuddy.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.budgetbuddy.model.Categoria;
import br.com.fiap.budgetbuddy.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categoria")
@Slf4j
public class CategoriaController {

    @Autowired //CDI - Context Dependence Injection
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public Categoria create(@RequestBody Categoria categoria){ 
        log.info("cadastrando categoria " + categoria);
        return repository.save(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("buscar categoria por id {} ", id);

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando categoria com id {} ", id);
        
        extracted(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizar categoria com id {} para {} ", id, categoria);

        extracted(id);
        categoria.setId(id);
        return repository.save(categoria);
    }

    private void extracted(Long id) {
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe categoria com o id informado"));
    }

}