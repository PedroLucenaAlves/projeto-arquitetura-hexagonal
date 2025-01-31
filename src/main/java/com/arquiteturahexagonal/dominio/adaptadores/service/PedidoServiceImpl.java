package com.arquiteturahexagonal.dominio.adaptadores.service;

import com.arquiteturahexagonal.dominio.Produto;
import com.arquiteturahexagonal.dominio.dtos.EstoqueDTO;
import com.arquiteturahexagonal.dominio.dtos.ProdutoDTO;
import com.arquiteturahexagonal.dominio.portas.interfaces.ProdutoServicePort;
import com.arquiteturahexagonal.dominio.portas.repositories.ProdutoRepositoryPort;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Serivco implementa a porta que nos possibilita acessar a regra de negocios
 * Nao temos um @service porque a regra de negocio (domino) esta isolada de componentes externos (incluindo o framework do Spring)
 */

public class PedidoServiceImpl implements ProdutoServicePort {

        private final ProdutoRepositoryPort produtoRepository;

        public PedidoServiceImpl(ProdutoRepositoryPort produtoRepository) {
            this.produtoRepository = produtoRepository;
        }

        @Override
        public void criarProduto(ProdutoDTO produtoDTO) {
            Produto produto = new Produto(produtoDTO);
            this.produtoRepository.salvar(produto);
        }

        @Override
        public List<ProdutoDTO> buscarProdutos() {
            List<Produto> produtos = this.produtoRepository.buscarTodos();
            return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
        }

        @Override
        public void atualizarEstoque(String sku, EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException {
            Produto produto = this.produtoRepository.buscarPeloSku(sku);

            if (Objects.isNull(produto))
                throw new ChangeSetPersister.NotFoundException();

            produto.atualizarEstoque(estoqueDTO.getQuantidade());

            System.out.println("Atualizando estoque para: " + produto.getQuantidade());

            this.produtoRepository.salvar(produto);
        }

}

