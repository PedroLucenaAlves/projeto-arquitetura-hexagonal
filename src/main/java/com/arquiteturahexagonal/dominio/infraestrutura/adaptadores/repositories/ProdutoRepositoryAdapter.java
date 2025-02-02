package com.arquiteturahexagonal.dominio.infraestrutura.adaptadores.repositories;

import com.arquiteturahexagonal.dominio.Produto;
import com.arquiteturahexagonal.dominio.infraestrutura.adaptadores.entidades.ProdutoEntity;
import com.arquiteturahexagonal.dominio.portas.repositories.ProdutoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component //indica que a classe deve ser exposta como um bean (deve fazer a injecao de dependencia)
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoRepositoryAdapter(SpringProdutoRepository springProdutoRepository){
        this.springProdutoRepository = springProdutoRepository;
    }


    @Override
    public List<Produto> buscarTodos() {
        List<ProdutoEntity> produtoEntities = this.springProdutoRepository.findAll();
        return produtoEntities.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto buscarPeloSku(String sku) {
        Optional<ProdutoEntity> produtoEntity = this.springProdutoRepository.findBySku(sku);

        if (produtoEntity.isPresent())
            return produtoEntity.get().toProduto();

        throw new RuntimeException("Produto não existe");
    }

    @Override
    public void salvar(Produto produto) {
        ProdutoEntity produtoEntity;
        if (Objects.isNull(produto.getCodigo()))
            produtoEntity = new ProdutoEntity(produto);
        else {
            produtoEntity = this.springProdutoRepository.findById(produto.getCodigo()).get();
            produtoEntity.atualizar(produto);
        }

        System.out.println("Entidade Produto: " + produtoEntity);
        this.springProdutoRepository.save(produtoEntity);

    }

    @Override
    public void deletar(Produto produto){

        ProdutoEntity produtoEntity = new ProdutoEntity(produto);

        this.springProdutoRepository.delete(produtoEntity);

    }

}
