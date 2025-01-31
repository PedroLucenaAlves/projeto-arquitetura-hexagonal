package com.arquiteturahexagonal.dominio;

import com.arquiteturahexagonal.dominio.dtos.ProdutoDTO;

import java.util.UUID;

/**
 * Qualquer coisa relacionada à nossa lógica de negócios passará por esta classe.
 *Acesso e feito as regras de negocio atraves das portas (interfaces)
 */

public class Produto {

    private UUID codigo;
    private String sku;
    private String nome;
    private Double preco;
    private Double quantidade;

    public Produto() {
    }

    public Produto(UUID codigo, String sku, String nome, Double quantidade, Double preco) {
        this.codigo = codigo;
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    //devolve produto atraves do produtoDTO
    public Produto(ProdutoDTO produtoDTO) {
        this.sku = produtoDTO.sku();
        this.nome = produtoDTO.nome();
        this.preco = produtoDTO.preco();
        this.quantidade = produtoDTO.quantidade();
        System.out.println(produtoDTO.quantidade());
    }

    public void atualizarEstoque(double quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoDTO toProdutoDTO() {
        return new ProdutoDTO(this.sku, this.nome, this.preco, this.quantidade);
    }

}
