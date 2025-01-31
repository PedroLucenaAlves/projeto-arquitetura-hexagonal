package com.arquiteturahexagonal.dominio.portas.repositories;

import com.arquiteturahexagonal.dominio.Produto;

import java.util.List;

/**
 * Porta para fazer persistencia e busca no banco
 */

public interface ProdutoRepositoryPort {

    List<Produto> buscarTodos();

    Produto buscarPeloSku(String sku);

    void salvar(Produto produto);

}
