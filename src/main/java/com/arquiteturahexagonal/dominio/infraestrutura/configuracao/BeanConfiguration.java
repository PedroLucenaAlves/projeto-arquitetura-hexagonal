package com.arquiteturahexagonal.dominio.infraestrutura.configuracao;

import com.arquiteturahexagonal.dominio.adaptadores.service.PedidoServiceImpl;
import com.arquiteturahexagonal.dominio.portas.interfaces.ProdutoServicePort;
import com.arquiteturahexagonal.dominio.portas.repositories.ProdutoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean //instanciando a classe de servico passando o repositorio, permitindo que o service seja utilizado como injecao de dependencia no controller
    ProdutoServicePort produtoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new PedidoServiceImpl(produtoRepositoryPort);
    }

}
