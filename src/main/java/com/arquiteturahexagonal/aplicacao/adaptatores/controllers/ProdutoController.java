package com.arquiteturahexagonal.aplicacao.adaptatores.controllers;

import com.arquiteturahexagonal.dominio.dtos.EstoqueDTO;
import com.arquiteturahexagonal.dominio.dtos.ProdutoDTO;
import com.arquiteturahexagonal.dominio.portas.interfaces.ProdutoServicePort;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe faz parte da camada aplicacao (nao faz parte do dominio), por isso temos as anotacoes
 */

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoServicePort produtoServicePort;

    public ProdutoController(ProdutoServicePort produtoServicePort) {
        this.produtoServicePort = produtoServicePort;
    }

    @PostMapping
    void criarProdutos(@RequestBody ProdutoDTO produtoDTO) {
        produtoServicePort.criarProduto(produtoDTO);
    }

    @GetMapping
    List<ProdutoDTO> getProdutos() {
        return produtoServicePort.buscarProdutos();
    }

    @PutMapping(value = "/{sku}")
    void atualizarEstoque(@PathVariable String sku, @RequestBody EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException {
        System.out.println("Recebido no Controller: " + estoqueDTO.getQuantidade());
    }
}
