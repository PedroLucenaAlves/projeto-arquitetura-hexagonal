package com.arquiteturahexagonal.dominio.portas.interfaces;

import com.arquiteturahexagonal.dominio.dtos.EstoqueDTO;
import com.arquiteturahexagonal.dominio.dtos.ProdutoDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

/**
 * Interface para uma classe de servico utilizada para buscar informacoes dos produtos
 */

public interface ProdutoServicePort {

    List<ProdutoDTO> buscarProdutos();

    void criarProduto(ProdutoDTO produtoDTO);

    void atualizarEstoque(String sku, EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException;

}
