package com.arquiteturahexagonal.dominio.infraestrutura.adaptadores.entidades;

import com.arquiteturahexagonal.dominio.Produto;
import jakarta.persistence.*;
import java.util.UUID;

/**
 * Entidade precicsa estar desacoplada da regra de negocio para seguir o padrao
 */

    @Entity
    @Table(name = "produtos")
    public class ProdutoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID codigo;

        private String sku;

        private String nome;

        private Double preco;

        private Double quantidade;

        public ProdutoEntity() {
        }

        public ProdutoEntity(Produto produto) {
            this.codigo = produto.getCodigo();
            this.sku = produto.getSku();
            this.nome = produto.getNome();
            this.preco = produto.getPreco();
            this.quantidade = produto.getQuantidade();
        }

        public void atualizar(Produto produto) {
            this.sku = produto.getSku();
            this.nome = produto.getNome();
            this.preco = produto.getPreco();
            this.quantidade = produto.getQuantidade();
        }

        public Produto toProduto() {
            return new Produto(this.codigo, this.sku, this.nome, this.quantidade, this.preco);
        }

}
